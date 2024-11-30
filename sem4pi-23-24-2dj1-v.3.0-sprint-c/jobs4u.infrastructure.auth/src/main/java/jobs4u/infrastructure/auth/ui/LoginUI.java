package jobs4u.infrastructure.auth.ui;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.infrastructure.auth.controller.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class LoginUI extends AbstractUI {

    @Autowired
    private LoginController controller;

    @Override
    protected boolean doShow() {
        try {


            Scanner in = new Scanner(System.in);
            String rawUsername, rawPassword;
            int attempt = 0;
            do {
                System.out.println("Enter your username: ");
                rawUsername = in.nextLine();
                System.out.println("Enter your password: ");
                rawPassword = in.nextLine();
                attempt++;
                if (!controller.authenticate(rawUsername, rawPassword)) {
                    System.out.println("Incorrect credentials, try again (" + (3 - attempt) + " attempts left).");
                }
            } while (attempt != 3 && !controller.authenticate(rawUsername, rawPassword));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        return true;
    }

    @Override
    public String headline() {
        return "Login";
    }

}
