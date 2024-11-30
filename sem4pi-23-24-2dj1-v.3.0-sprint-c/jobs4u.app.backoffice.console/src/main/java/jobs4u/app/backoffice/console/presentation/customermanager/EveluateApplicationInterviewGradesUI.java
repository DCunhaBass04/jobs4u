package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.backoffice.application.EveluateApplicationInterviewGradesController;
import jobs4u.core.jobopening.domain.JobOpening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class EveluateApplicationInterviewGradesUI extends AbstractUI {

    @Autowired
    private EveluateApplicationInterviewGradesController controller;
    @Override
    protected boolean doShow() {
        JobOpening chosenJobOpening = chooseJobOpening();
        if(chosenJobOpening != null)
            try {
                controller.executeProcess(chosenJobOpening);
            } catch (MalformedURLException | URISyntaxException | ClassNotFoundException | InvocationTargetException
                     | NoSuchMethodException | InstantiationException | IllegalStateException |
                     IllegalAccessException e) {
                System.out.println("Error: "+e.getMessage());
            }
        else
            System.out.println("Exiting!");
        return false;
    }

    private JobOpening chooseJobOpening(){
        List<JobOpening> jobOpenings = controller.getOpeningsInInterviewsPhase();
        if(jobOpenings.isEmpty()) {
            System.out.println("You have no registered Job Openings in the Interviews phase.");
            return null;
        }
        System.out.println("Pick a Job Opening (0 to exit):");
        for(int i = 0; i < jobOpenings.size(); i++)
            System.out.printf(" %d - %s%n", (i+1), jobOpenings.get(i).getJobReference());
        System.out.println();
        int option = Console.readOption(1, jobOpenings.size(), 0);
        if (option == 0) {
            return null;
        }
        return jobOpenings.get(option - 1);
    }


    @Override
    public String headline() {return "Execute Grades For Interview Grades" ;}
}

