package jobs4u.app.backoffice.console.presentation.langengineer;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.candidate.application.DisplayCandidateApplicationsController;
import jobs4u.core.interviewmodel.application.DeployInterviewModelPluginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeployInterviewModelPluginUI extends AbstractUI {

    @Autowired
    private DeployInterviewModelPluginController controller;

    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return "Deploy interview model plugin";
    }
}
