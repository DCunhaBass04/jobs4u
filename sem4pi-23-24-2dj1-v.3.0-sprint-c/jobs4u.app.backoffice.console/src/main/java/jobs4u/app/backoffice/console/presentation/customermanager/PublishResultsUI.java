package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.backoffice.application.PublishResultsController;
import jobs4u.core.backoffice.application.RankCandidatesController;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class PublishResultsUI extends AbstractUI {

    @Autowired
    private PublishResultsController controller;

    @Override
    protected boolean doShow(){
        Customer customer = chooseCustomer();
        JobOpening jobOpening = chooseJobOpening(customer);
        List<Rank> results = controller.getResults(jobOpening);
        controller.publishResults(results);
        return false;
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
        return "Publish the results of the selection process.";
    }

}
