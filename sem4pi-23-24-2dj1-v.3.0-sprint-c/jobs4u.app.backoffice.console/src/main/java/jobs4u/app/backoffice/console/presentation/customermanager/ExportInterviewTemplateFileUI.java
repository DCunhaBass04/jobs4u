package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.candidate.application.DisplayCandidateApplicationsController;
import jobs4u.core.interviewmodel.application.ExportInterviewTemplateFileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExportInterviewTemplateFileUI extends AbstractUI {

    @Autowired
    private ExportInterviewTemplateFileController controller;

    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return "Export interview model template file";
    }

}