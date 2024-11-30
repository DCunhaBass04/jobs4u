package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.backoffice.application.RankCandidatesController;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobopening.application.ListJobOpeningsController;
import jobs4u.core.jobopening.domain.JobOpening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@Transactional(readOnly = true)
public class RankCandidatesUI extends AbstractUI{

    @Autowired
    private RankCandidatesController controller;

    private double MULTIPLIER = 2;

    @Override
    protected boolean doShow(){
        Customer customer = chooseCustomer();
        JobOpening jobOpening = chooseJobOpening(customer);
        showCandidates(jobOpening);
        rankCandidates(jobOpening);
        return false;
    }

    private void showCandidates(JobOpening jobOpening) {
        List<Candidate> candidates = controller.findCandidatesByJobOpening(jobOpening);
        System.out.printf("Candidate List:%n");
        for (int i = 0; i < candidates.size(); i++) {
            System.out.printf("%d - %s %s%n",i,candidates.get(i).getName().firstName(),candidates.get(i).getName().lastName());
        }
    }

    public void rankCandidates(JobOpening jobOpening) {
        Scanner scanner = new Scanner(System.in);
        List<JobApplication> jobApplicationList = controller.getApplicationList(jobOpening);
        List<Integer> usedOptions = new ArrayList<>();
        int option;

        for (int i = 0; i < numberOfRankings(jobApplicationList) && i < jobApplicationList.size(); i++) {
            System.out.printf("Please select a candidate:%n");
            boolean verificationSuccessful = false;
            do{
                try{
                    option = scanner.nextInt();
                    controller.insertedNumberisAValidOption(jobApplicationList.size(), option);
                    controller.candidateIsNotRanked(usedOptions, option);
                    verificationSuccessful = true;
                    usedOptions.add(option);
                    controller.rankApplication(jobOpening, jobApplicationList.get(i), i);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("\nPlease try again.\n");
                }
            } while (!verificationSuccessful);
        }
    }

    public int numberOfRankings(List<JobApplication> jobApplications){
        JobApplication application = jobApplications.get(0);
        int vacancies;
        JobOpening jobOpening = application.getJobOpening();
        vacancies = jobOpening.getNumberOfVacancies().getNumberOfVacancies();
        return (int) (vacancies*MULTIPLIER);
    }

    private Customer chooseCustomer(){
        if(!Console.readBoolean("Do you want to filter by customer? (y/n)"))
            return null;
        List<Customer> customers = controller.findCustomersFromCustomerManager();
        if(customers.isEmpty()){
            System.out.println("You have no registered Customers.");
            return null;
        }
        System.out.println("Pick a Customer (0 to exit):");
        for (int i = 0; i < customers.size(); i++)
            System.out.printf(" %d - %s%n", (i+1), customers.get(i).getCustomerCode());
        System.out.println();
        int option = Console.readOption(1, customers.size(), 0);
        if (option == 0) {
            return null;
        }
        return customers.get(option - 1);
    }
    private JobOpening chooseJobOpening(Customer customer){
        List<JobOpening> jobOpenings;
        if(customer == null) jobOpenings = controller.findJobOpeningFromCustomerManager();
        else jobOpenings = controller.findJobOpeningFromCustomerManagerByCustomer(customer);
        if(jobOpenings.isEmpty()) {
            System.out.println("You have no registered Job Openings.");
            return null;
        }
        System.out.println("Pick a Job Opening (0 to exit):");
        for(int i = 0; i < jobOpenings.size(); i++)
            System.out.printf(" %d - %s%n", (i+1), jobOpenings.get(i).getJobReference());
        System.out.println();
        int option = Console.readOption(1, jobOpenings.size(), 0);
        if (option == 0) {
            return null;
        }
        return jobOpenings.get(option - 1);
    }

    @Override
    public String headline() {
        return "Rank candidates for a job application";
    }

}
