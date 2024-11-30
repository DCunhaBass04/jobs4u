package jobs4u.app.language.grammar.presentation.languagePlugin.menu.interview;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.app.language.grammar.presentation.languagePlugin.menu.interview.IM1.IM1Evaluator;
import org.springframework.stereotype.Component;
import utils.Utils;

import java.io.IOException;
import java.util.Scanner;

@Component
public class InterviewPluginUI extends AbstractUI {
    @Override
    protected boolean doShow() {
        Scanner ler = new Scanner(System.in);
        System.out.printf("Select an option:%n1 - Export template file%n2 - Evaluate file%n");
        IM1Evaluator evaluator = new IM1Evaluator();
        switch(ler.nextInt()){
            case 1 :
                ler.nextLine();
                System.out.println("Write the path and name of the file where you want to save the template.");
                System.out.println(evaluator.exportTemplate());
                break;
            case 2 :
                System.out.println("Write the path to the file you want to evaluate:");
                ler.nextLine();
                try {
                    System.out.println(evaluator.evaluateTextFile(Utils.readFromInputStream(ler.nextLine())));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
        return false;
    }

    @Override
    public String headline() {
        return "Interview Menu";
    }
}
