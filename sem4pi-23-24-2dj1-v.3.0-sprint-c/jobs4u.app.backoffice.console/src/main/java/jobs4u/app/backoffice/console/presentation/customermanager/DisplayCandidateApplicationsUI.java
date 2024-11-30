package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.candidate.application.DisplayCandidateApplicationsController;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.jobapplication.domain.JobApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Component
public class DisplayCandidateApplicationsUI extends AbstractUI {

    @Autowired
    private DisplayCandidatePersonalDataUI personalDataUI;

    @Autowired
    private DisplayCandidateApplicationsController controller;

    @Override
    protected boolean doShow() {
        personalDataUI.doShow();
        Candidate candidate = personalDataUI.getCandidate();
        List<JobApplication> jobApplications = controller.findApplicationsByCandidate(candidate);
        try {
            for(JobApplication application : jobApplications) {
                System.out.printf("%n%s%n", application);
                for (Map.Entry<String, Map.Entry<List<String>, Integer>> entry : controller.createListOfTopWords(application)) {
                    System.out.printf("Word: \"%s\"%nNumber of occurrences: %d%nPlaces where this word appeared:%n", entry.getKey(), entry.getValue().getValue());
                    for (String fileName : entry.getValue().getKey()) System.out.printf("-> %s%n", fileName);
                    System.out.println();
                }
            }
        } catch (InterruptedException | RuntimeException e) {
            System.out.printf("An error occurred: %s%n", e.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Display candidate data and applications";
    }
}
