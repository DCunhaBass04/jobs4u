package jobs4u.app.backoffice.console.presentation.admin;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.EnumSelectWidget;
import jobs4u.core.backoffice.application.RegisterBackofficeUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class RegisterBackofficeUserUI extends AbstractUI {

    @Autowired
    private RegisterBackofficeUserController controller;

    @Override
    protected boolean doShow() {
        Scanner scanner = new Scanner(System.in);
        String role, username, password, email;
        int roleOption;
        ArrayList<String> roles = new ArrayList<>();
        makeAndShowRoleList(roles);
        System.out.print("Select a role.%n");
        boolean validRole;
        do {
            validRole = true;
            roleOption = scanner.nextInt();
            role = roles.get(roleOption);
            if (roleOption >= roles.size() || roleOption < 0){
                validRole = false;
                System.out.printf("Option invalid. Please try again.");
            }
        } while (!validRole);
        System.out.print("Username:\t");
        username = scanner.next();
        boolean registrationSuccessful = false;

        do{
            try{
                System.out.print("Password:\t");
                password = scanner.next();
                controller.passwordIsSatisfiedBy(password);

                System.out.print("Email:\t");
                email = scanner.next();
                controller.emailIsSatisfiedBy(email);

                controller.registerUser(role,username,password,email);
                registrationSuccessful = true;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("\nPlease try again.\n");
            }
        }while(!registrationSuccessful);
        return false;
    }

    private static void makeAndShowRoleList(ArrayList<String> roles){
        roles.add("CUSTOMER_MANAGER");
        roles.add("OPERATOR");
        roles.add("LANGUAGE_ENGINEER");
        for (int i = 0; i < roles.size(); i++) {
            System.out.printf("%d - %s%n", i, roles.get(i));
        }
    }

    @Override
    public String headline() {
        return "Register backoffice user";
    }
}
