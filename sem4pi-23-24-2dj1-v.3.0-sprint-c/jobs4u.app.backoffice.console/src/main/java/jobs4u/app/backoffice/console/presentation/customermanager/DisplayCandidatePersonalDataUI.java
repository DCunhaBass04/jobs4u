package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.candidate.application.DisplayCandidateApplicationsController;
import jobs4u.core.candidate.application.DisplayCandidatePersonalDataController;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class DisplayCandidatePersonalDataUI extends AbstractUI {

    @Autowired
    private DisplayCandidatePersonalDataController controller;

    @Getter
    private Candidate candidate;

    @Override
    protected boolean doShow() {
        Scanner scanner = new Scanner(System.in);
        String firstName, lastName;
        List<Candidate> candidates = controller.getCandidates();
        for (int i = 0; i < candidates.size(); i++) {
            System.out.printf("%d - %s%n", i, candidates.get(i).getName());
        }

        System.out.printf("Please write the name of the candidate.%n");
        boolean verificationSuccessful = false;
        do {
            try {
                firstName = scanner.nextLine();
                controller.firstNameIsSatisfiedBy(firstName);

                lastName = scanner.nextLine();
                controller.lastNameIsSatisfiedBy(lastName);

                candidate = controller.getCandidateByName(firstName, lastName);
                System.out.printf("%s", candidate.toString());
                verificationSuccessful = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("\nPlease try again.\n");
            }
        } while (!verificationSuccessful);
        return false;
    }

    @Override
    public String headline() {
        return "Display candidate personal data";
    }
}
