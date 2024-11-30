package jobs4u.app.language.grammar.presentation.languagePlugin.menu.requirements;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.app.language.grammar.presentation.languagePlugin.menu.requirements.Req1.Req1Evaluator;
import jobs4u.app.language.grammar.presentation.languagePlugin.menu.requirements.Req1.ReqEvaluator;
import org.springframework.stereotype.Component;
import utils.Utils;

import java.io.IOException;
import java.util.Scanner;

@Component
public class RequirementPluginUI extends AbstractUI {
    @Override
    protected boolean doShow() {
        System.out.printf("Select an option:%n1 - Export template file%n2 - Evaluate file%n");
        Scanner ler = new Scanner(System.in);
        ReqEvaluator evaluator = new Req1Evaluator();
        switch(ler.nextInt()){
            case 1 :
                ler.nextLine();
                System.out.println("Write the path and name of the file where you want to save the template.");
                System.out.println(evaluator.exportTemplate());
                break;
            case 2 :
                try {
                    System.out.println("Write the path to the file you want to evaluate:");
                    ler.nextLine();
                    evaluator.evaluateTextFile(Utils.readFromInputStream(ler.nextLine()));
                    System.out.println("Passed!");
                } catch (IllegalStateException | IOException e) {System.out.println("ERROR: " +e.getMessage());}
                break;
        }
        return false;
    }

    @Override
    public String headline() {return "Requirement Menu";}
}
