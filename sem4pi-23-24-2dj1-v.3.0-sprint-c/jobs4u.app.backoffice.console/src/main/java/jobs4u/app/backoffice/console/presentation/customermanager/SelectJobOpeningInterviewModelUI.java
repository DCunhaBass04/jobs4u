package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.interviewmodel.domain.InterviewModel;
import jobs4u.core.interviewmodel.domain.InterviewModelDesignation;
import jobs4u.core.jobopening.application.SelectJobOpeningInterviewModelController;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectJobOpeningInterviewModelUI extends AbstractUI {

    @Autowired
    private SelectJobOpeningInterviewModelController controller;

    @Autowired
    private ListInterviewModelsUI listInterviewModelsUI;

    @Override
    protected boolean doShow() {

        try {
            // retirei list Ui , deixas se quiseres depois

            //listInterviewModelsUI.doShow();
            JobOpening jobOpening = chooseJobOpening();
            InterviewModel interviewModel = chooseInterviewModel();

            controller.setInterviewModelForJobOpening(jobOpening.getJobReference(), interviewModel.identity());
            System.out.println("Interview Model selected successfully");
            return false;


        } catch (DataIntegrityViolationException e) {
            System.out.println("There is already a Job Opening with that Interview Model");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private JobOpening chooseJobOpening() {
        List<JobOpening> jobOpenings = controller.findCustomerManagerJobOpenings();
        if (jobOpenings.isEmpty()) {
            System.out.println("You have no registered Job Openings.");
            return null;
        }
        System.out.println("Pick a Job Opening (0 to exit):\n");
        for (int i = 0; i < jobOpenings.size(); i++)
            System.out.printf(" %d - %s%n", (i + 1), jobOpenings.get(i).getJobReference());
        System.out.println();
        int option = Console.readOption(1, jobOpenings.size(), 0);
        if (option == 0) {
            return null;
        }
        return jobOpenings.get(option - 1);
    }

    private InterviewModel chooseInterviewModel() {
        List<InterviewModel> interviewModels = controller.findInterviewModels();
        if (interviewModels.isEmpty()) {
            System.out.println("There are no registered Interview Models");
            return null;
        }
        System.out.println("Pick a Interview Model (0 to exit):");
        for (int i = 0; i < interviewModels.size(); i++)
            System.out.printf(" %d - %s%n", (i + 1), interviewModels.get(i));
        System.out.println();
        int option = Console.readOption(1, interviewModels.size(), 0);
        if (option == 0) {
            return null;
        }
        return interviewModels.get(option - 1);
    }

    @Override
    public String headline() {
        return "Select job opening interview model";
    }

}
