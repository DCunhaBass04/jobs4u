package jobs4u.app.backoffice.console.presentation.admin;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.backoffice.application.DisableBackofficeUserController;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class DisableBackofficeUserUI extends AbstractUI {

    @Autowired
    private DisableBackofficeUserController controller;

    @Override
    protected boolean doShow() {
        Scanner scanner = new Scanner(System.in);
        String email;
        List<Jobs4uUser> backofficeUsers = controller.getBackofficeUsers();
        backofficeUsers.forEach(System.out::println);
        System.out.printf("Please type the email of the user you want to disable:%n");
        boolean verificationSuccessful = false;
        do{
            try{
                email = scanner.next();
                controller.emailIsSatisfiedBy(email);
                controller.findUserAndToggleStatus(email);
                verificationSuccessful = true;
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("\nPlease try again.\n");
            }
        } while (!verificationSuccessful);
        return false;
    }

    @Override
    public String headline() {
        return "Disable backoffice user";
    }
}
