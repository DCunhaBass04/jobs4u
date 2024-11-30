package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.EnumSelectWidget;
import jobs4u.core.interviewmodel.domain.InterviewModel;
import jobs4u.core.jobopening.application.EditJobOpeningController;
import jobs4u.core.jobopening.application.SelectJobOpeningInterviewModelController;
import jobs4u.core.jobopening.application.SelectJobRequirementsController;
import jobs4u.core.jobopening.domain.ContractType;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.JobOpeningMode;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class EditJobOpeningUI extends AbstractUI {

    @Autowired
    EditJobOpeningController controller;
    @Autowired
    SelectJobOpeningInterviewModelController interviewModelController;
    @Autowired
    SelectJobRequirementsController jobRequirementsController;


    @Override
    protected boolean doShow() {

        JobOpening jobOpening = chooseJobOpening();

        jobOpening.isOnCorrectPhases();


        if (jobOpening.getRecruitmentProcess().getCurrentPhase() == null) {

            String functionRaw = Console.readLine("Enter a new function ");
            EnumSelectWidget<ContractType> contractTypeEnumSelectWidget = new EnumSelectWidget<>("Edit contract Type:", ContractType.class);
            contractTypeEnumSelectWidget.show();


            EnumSelectWidget<JobOpeningMode> jobOpeningModeEnumSelectWidget = new EnumSelectWidget<>("Edit Mode:", JobOpeningMode.class);
            jobOpeningModeEnumSelectWidget.show();
            String jobOpeningAdressRaw = Console.readLine("Enter a new Address: ");
            Integer numberOfVacancies = Console.readInteger("Edit Number of Vacancies");

            controller.editJobOpeningWithNoPhase(jobOpening, functionRaw, contractTypeEnumSelectWidget.selectedElement(), jobOpeningModeEnumSelectWidget.selectedElement(), jobOpeningAdressRaw, numberOfVacancies);
            System.out.println("Job Opening successvely edited ");
            return false;
        } else if ( jobOpening.getRecruitmentProcess().getCurrentPhase().getRecruitmentPhaseDesignation().name().equals("APPLICATION")) {

            InterviewModel interviewModel = chooseNewInterviewModel();
            JobRequirements jobRequirements = chooseJobRequirements();
            controller.editJobOpeningOnApplicationPhase(jobOpening,interviewModel,jobRequirements);

        }
        System.out.println("Job Opening successvely edited ");
        return false;

    }






    private JobOpening chooseJobOpening () {

        List<JobOpening> jobOpenings = controller.findCustomerManagerJobOpenings();
        if (jobOpenings.isEmpty()) {
            System.out.println("You have no registered Job Openings.");
            return null;
        }
        System.out.println("Pick a Job Opening (0 to exit):\n");
        for (int i = 0; i < jobOpenings.size(); i++)
            System.out.printf(" %d - %s%n", (i + 1), jobOpenings.get(i));
        System.out.println();
        int option = Console.readOption(1, jobOpenings.size(), 0);
        if (option == 0) {
            return null;
        }
        return jobOpenings.get(option - 1);

    }


    /**
     * Code repated to garantuee the consistency of each use Case having a single responsability
     *
     * @return
     */


    private JobRequirements chooseJobRequirements () {
        List<JobRequirements> jobRequirements = jobRequirementsController.findJobRequirements();
        if (jobRequirements.isEmpty()) {
            System.out.println("There no available Job requirements.");
            return null;
        }
        System.out.println("Pick a Job Requirement (0 to exit):");
        for (int i = 0; i < jobRequirements.size(); i++)
            System.out.printf(" %d - %s%n", (i + 1), jobRequirements.get(i));
        System.out.println();
        int option = Console.readOption(1, jobRequirements.size(), 0);
        if (option == 0) {
            return null;
        }
        return jobRequirements.get(option - 1);
    }

    private InterviewModel chooseNewInterviewModel () {
        List<InterviewModel> interviewModels = interviewModelController.findInterviewModels();
        if (interviewModels.isEmpty()) {
            System.out.println("There are no available Interview Models");
            return null;
        }
        System.out.println("Select a new Interview Model (0 to exit):");
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
        return "Edit a Job Opening";

    }

}








