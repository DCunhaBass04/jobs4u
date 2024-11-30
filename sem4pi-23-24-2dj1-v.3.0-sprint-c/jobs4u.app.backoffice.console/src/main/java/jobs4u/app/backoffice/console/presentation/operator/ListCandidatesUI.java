package jobs4u.app.backoffice.console.presentation.operator;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.candidate.application.DisplayCandidateApplicationsController;
import jobs4u.core.candidate.application.ListCandidatesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListCandidatesUI extends AbstractUI {

    @Autowired
    private ListCandidatesController controller;

    @Override
    protected boolean doShow() {
        controller.findAllCandidates().forEach(System.out::println);
        return false;
    }

    @Override
    public String headline() {
        return "List candidates";
    }
}
