package jobs4u.app.candidate.console.menu;

import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements Runnable {
    private final ArrayList<AbstractUI> options;

    public Menu() {
        options = new ArrayList<>();
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        boolean exits = false;
        int option = -1;
        do {
            try {
                display();
                option = in.nextInt();
                if (option != 0) {
                    exits = options.get(option - 1).show();
                }
            } catch (InputMismatchException e) {
                System.out.println("Not a valid option.");
                in.next();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("That option doesn't exist.");
            }
        } while (option != 0 && !exits);
        options.clear();
    }

    public void addOption(AbstractUI option) {
        options.add(option);
    }

    private void display() {
        int i = 1;
        for (AbstractUI option : options) {
            System.out.printf("%d - %-80s\n", i, option.headline());
            i++;
        }
        System.out.println("\n0 - Quit\n");
        System.out.println("=".repeat(80));
        System.out.println();
    }
}