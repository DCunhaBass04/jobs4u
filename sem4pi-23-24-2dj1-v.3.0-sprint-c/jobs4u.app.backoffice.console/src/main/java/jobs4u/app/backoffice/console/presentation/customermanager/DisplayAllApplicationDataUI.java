package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.jobapplication.application.DisplayAllApplicationDataController;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobopening.domain.ContractType;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.JobOpeningMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DisplayAllApplicationDataUI extends AbstractUI {
    @Autowired
    private DisplayAllApplicationDataController controller;
    @Override
    protected boolean doShow() {
        Object chosenFilter = chooseFilterStyle(), chosenObject;
        try {
            if (!(chosenFilter instanceof Candidate) && chosenFilter != null)
                chosenObject = chooseJobOpening(chosenFilter);
            else chosenObject = chosenFilter;
            JobApplication application = chooseApplication(chosenObject);
            if (application != null) System.out.println(application.details());
        } catch (IllegalStateException e) {
            System.out.printf("%s%nExiting!%n",e.getMessage());
        }
        return false;
    }
    private Object chooseFilterStyle(){
        System.out.printf("Do you wish to filter by:%n 1 - Candidate%n 2 - Job Opening%n 0 - None%n");
        return switch (Console.readOption(1, 2, 0)) {
            case 1 -> chooseCandidate();
            case 2 -> chooseOpeningFilterStyle();
            default -> null;
        };
    }
    private Candidate chooseCandidate(){
        List<Candidate> candidates = controller.findCandidates();
        if(candidates.isEmpty()) throw new IllegalStateException("There are no registered Candidates.");
        System.out.println("Pick a Candidate (0 to exit):");
        for(int i = 0; i < candidates.size(); i++)
            System.out.printf(" %d - %s%n", (i+1), candidates.get(i));
        System.out.println();
        int option = Console.readOption(1, candidates.size(), 0);
        if (option == 0) {
            return null;
        }
        return candidates.get(option - 1);
    }
    private Object chooseOpeningFilterStyle(){
        System.out.printf("Do you wish to filter by:%n 1 - Customer%n 2 - Contract Type%n 3 - Mode%n 0 - None%n");
        return switch (Console.readOption(1, 3, 0)) {
            case 1 -> chooseCustomer();
            case 2 -> chooseContractType();
            case 3 -> chooseMode();
            default -> null;
        };
    }
    private Customer chooseCustomer(){
        List<Customer> customers = controller.findCustomersWithCurrentManager();
        if(customers.isEmpty()) throw new IllegalStateException("You have no registered Customers.");
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
    private ContractType chooseContractType(){
        System.out.printf("Pick a Contract Type (0 to exit):%n 1 - Full-time%n 2 - Part-time%n");
        return switch (Console.readOption(1, 2, 0)) {
            case 1 -> ContractType.FULL_TIME;
            case 2 -> ContractType.PART_TIME;
            default -> null;
        };
    }
    private JobOpeningMode chooseMode(){
        System.out.printf("Pick a Mode (0 to exit):%n 1 - Onsite%n 2 - Remote%n 3 - Hybrid%n");
        return switch (Console.readOption(1, 3, 0)) {
            case 1 -> JobOpeningMode.ONSITE;
            case 2 -> JobOpeningMode.REMOTE;
            case 3 -> JobOpeningMode.HYBRID;
            default -> null;
        };
    }
    private JobOpening chooseJobOpening(Object filter){
        List<JobOpening> jobOpenings;
        if(filter == null) jobOpenings = controller.findJobOpeningsByCustomerManager();
        else if (filter instanceof Customer) jobOpenings = controller.findJobOpeningsByCustomerManagerFromCustomer((Customer) filter);
        else if (filter instanceof ContractType) jobOpenings = controller.findJobOpeningsByCustomerManagerWithContractType((ContractType) filter);
        else jobOpenings = controller.findJobOpeningsByCustomerManagerWithMode((JobOpeningMode) filter);
        if(jobOpenings.isEmpty()) throw new IllegalStateException("You have no registered Job Openings under the established conditions.");
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
    private JobApplication chooseApplication(Object object){
        List<JobApplication> applications;
        if(object == null) applications = controller.findAllApplications();
        else if(object instanceof JobOpening) applications = controller.findJobApplicationsByJobOpening((JobOpening) object);
        else applications = controller.findJobApplicationsByCandidate((Candidate) object);
        if(applications.isEmpty()) throw new IllegalStateException("There are no registered applications for this job opening.");
        System.out.println("Pick an Application (0 to exit):");
        for(int i = 0; i < applications.size(); i++)
            System.out.printf(" %d - Application no. %d%n", (i+1), applications.get(i).identity());
        System.out.println();
        int option = Console.readOption(1, applications.size(), 0);
        if (option == 0) {
            return null;
        }
        return applications.get(option - 1);
    }
    @Override
    public String headline() {
        return "Display all data of an Application";
    }
}
