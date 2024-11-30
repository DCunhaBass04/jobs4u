package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.customer.application.RegisterCustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RegisterCustomerUI extends AbstractUI {

    @Autowired
    private RegisterCustomerController controller;


    @Override
    protected boolean doShow() {
        boolean registrationSuccessful = false;

        do {


            try {

                Scanner in = new Scanner(System.in);
                System.out.println("Enter the customer's username: ");
                String rawUsername = in.nextLine();
                System.out.println("Enter the customer's password: (At least 8 characters, one capital, one digit and one special character) ");
                String rawPassword = in.nextLine();
                System.out.println("Enter the customer's email: ");
                String rawEmail = in.nextLine();
                System.out.println("Enter the customer's first name: ");
                String firstName = in.nextLine();
                System.out.println("Enter the customer's last name: ");
                String lastName = in.nextLine();
                System.out.println("Enter a customer Code:\n (maximum 10 characters long, only capital letters,it can contain numbers)");
                String customerCode = in.nextLine();
                controller.registerCustomer(rawUsername, rawPassword, rawEmail, firstName, lastName, customerCode);
                System.out.println("Customer was registered successfully.");
                registrationSuccessful = true;

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());




            } catch (Exception e) {
                System.out.println("\nPlease try again. \n");

            }


        }while (!registrationSuccessful);
        return false;
    }


    @Override
    public String headline() {
        return "Register customer";
    }
}
