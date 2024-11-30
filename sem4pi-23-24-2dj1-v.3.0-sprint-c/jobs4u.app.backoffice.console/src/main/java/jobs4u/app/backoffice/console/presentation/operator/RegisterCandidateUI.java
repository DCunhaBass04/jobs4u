package jobs4u.app.backoffice.console.presentation.operator;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.candidate.application.RegisterCandidateController;
import jobs4u.core.candidate.domain.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterCandidateUI extends AbstractUI {

    @Autowired
    private RegisterCandidateController controller;

    @Override
    protected boolean doShow() {
        boolean registrationSuccessful = false;

        do {
            try {
                String rawUsername = Console.readLine("\nEnter the candidate's username : ");

                String rawPassword = Console.readLine("\nEnter the candidate's password (Must be at least 7 characters long, contain at least one capital letter and one number): ");
                controller.passwordIsSatisfiedBy(rawPassword);

                String rawEmail = Console.readLine("\nEnter the candidate's email (Must be in the format: username@domain.com): ");
                controller.emailIsSatisfiedBy(rawEmail);

                String firstName = Console.readLine("\nEnter the candidate's first name (Must start with a capital letter and contain only letters.): ");
                controller.firstNameIsSatisfiedBy(firstName);

                String lastName = Console.readLine("\nEnter the candidate's last name (Must start with a capital letter and contain only letters.): ");
                controller.lastNameIsSatisfiedBy(lastName);

                String rawPhoneNumber = Console.readLine("\nEnter the candidate's phone number (Must start with digit 9 followed by 8 digits): ");
                controller.phoneNumberIsSatisfiedBy(rawPhoneNumber);

                controller.registerCandidate(rawUsername, rawPassword, rawEmail, firstName, lastName, rawPhoneNumber);
                registrationSuccessful = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("\nPlease try again.\n");
            }

        } while (!registrationSuccessful);

        return false;
    }

    @Override
    public String headline() {
        return "Register a candidate";
    }
}
