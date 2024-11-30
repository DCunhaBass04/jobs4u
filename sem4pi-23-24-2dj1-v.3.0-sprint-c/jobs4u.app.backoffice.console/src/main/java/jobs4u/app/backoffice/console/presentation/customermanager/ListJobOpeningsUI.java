package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.jobopening.application.ListJobOpeningsController;
import jobs4u.core.jobopening.domain.JobOpening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class ListJobOpeningsUI extends AbstractUI {


    @Autowired
    private ListJobOpeningsController controller;

    @Override
    protected boolean doShow() {
        Customer chosenCustomer = chooseCustomer();
        List<JobOpening> jobOpenings;
        if(chosenCustomer == null)
            jobOpenings = controller.findAllCustomerManagerJobOpenings();
        else jobOpenings = controller.findJobOpeningFromCustomerManagerByCustomer(chosenCustomer);
        jobOpenings.forEach(System.out::println);
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
    @Override
    public String headline() {
        return "List job openings";
    }
}




