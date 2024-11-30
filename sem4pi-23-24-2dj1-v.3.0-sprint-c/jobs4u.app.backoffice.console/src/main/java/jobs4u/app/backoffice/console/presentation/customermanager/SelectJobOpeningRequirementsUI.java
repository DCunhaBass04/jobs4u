package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.jobopening.application.SelectJobRequirementsController;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectJobOpeningRequirementsUI extends AbstractUI {
    @Autowired
    private SelectJobRequirementsController controller;

    @Override
    protected boolean doShow() {
        try {
            JobOpening jobOpening = chooseJobOpening();
            JobRequirements jobRequirements = chooseJobRequirements();
            controller.saveJobOpeningWithRequirement(jobOpening, jobRequirements);
            System.out.println("Job Requirements were selected successfully");
            return false;


        } catch (DataIntegrityViolationException e) {
            System.out.println("There is already a Job Opening with that requirement designation.Please choose another one.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private JobOpening chooseJobOpening() {
        List<JobOpening> jobOpenings = controller.findJobOpeningsByCustomerManagerWithoutJobRequirement();
        if (jobOpenings.isEmpty()) {
            System.out.println("You have no registered Job Openings.");
            return null;
        }
        System.out.println("Pick a Job Opening (0 to exit):");
        for (int i = 0; i < jobOpenings.size(); i++)
            System.out.printf(" %d - %s%n", (i + 1), jobOpenings.get(i).getJobReference());
        System.out.println();
        int option = Console.readOption(1, jobOpenings.size(), 0);
        if (option == 0) {
            return null;
        }
        return jobOpenings.get(option - 1);
    }

    private JobRequirements chooseJobRequirements() {
        List<JobRequirements> jobRequirements = controller.findJobRequirements();
        if (jobRequirements.isEmpty()) {
            System.out.println("There are no registered Job requirements.");
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

    @Override
    public String headline() {
        return "Select job opening requirements";
    }
}
