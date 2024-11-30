package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.EnumSelectWidget;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.domain.CustomerCode;
import jobs4u.core.jobopening.application.RegisterJobOpeningController;
import jobs4u.core.jobopening.domain.ContractType;
import jobs4u.core.jobopening.domain.JobOpeningMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterJobOpeningUI extends AbstractUI {

    @Autowired
    private RegisterJobOpeningController controller;

    @Override
    protected boolean doShow() {

        boolean registrationSuccessful = false;

        do {
            try {


                Customer customer = chooseCustomerCode();
                System.out.println("Number of Job Openings of Customer" +controller.countJobOpeningByCustomer(customer));

                if (customer == null) {
                    System.out.println("Incorrect Customer Code. Please try again.");
                    return false;
                }


                String designationRaw = Console.readLine("Enter a function ");
                EnumSelectWidget<ContractType> contractTypeEnumSelectWidget = new EnumSelectWidget<>("Select a contract Type:", ContractType.class);
                contractTypeEnumSelectWidget.show();

                EnumSelectWidget<JobOpeningMode> jobOpeningModeEnumSelectWidget = new EnumSelectWidget<>("Select a Mode:", JobOpeningMode.class);
                jobOpeningModeEnumSelectWidget.show();
                String jobOpeningAdressRaw = Console.readLine("Enter a Address: ");

                Integer numberOfVacancies = Console.readInteger("Enter the Number of Vacancies");


                controller.registerJobOpening(designationRaw, contractTypeEnumSelectWidget.selectedElement(), jobOpeningModeEnumSelectWidget.selectedElement(), jobOpeningAdressRaw, numberOfVacancies, customer);
                System.out.println("Job Opening was registered successfully.");
                registrationSuccessful = true;


            } catch (DataIntegrityViolationException e) {
                System.out.println("There is already a Job Opening with that customer Code, please pick another one.");


            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("\nPlease try again. \n");
            }


        } while (!registrationSuccessful);

        return false;


    }

    private Customer chooseCustomerCode() {

        List<Customer> customers = controller.findCustomersFromCustomerManager();
        if (customers.isEmpty()) {
            System.out.println("You have no registered Customers.");
            return null;
        }

        System.out.println("Select an existing Customer for a JobOpening (0 to exit):\n");
        for (int i = 0; i < customers.size(); i++)
            System.out.printf(" %d - %s%n", (i + 1), customers.get(i));
            System.out.println();

        int option = Console.readOption(1, customers.size(), 0);
        if (option == 0) {
            return null;
        }
        return customers.get(option - 1);
    }


    @Override
    public String headline() {
        return "Register job opening";
    }
}

