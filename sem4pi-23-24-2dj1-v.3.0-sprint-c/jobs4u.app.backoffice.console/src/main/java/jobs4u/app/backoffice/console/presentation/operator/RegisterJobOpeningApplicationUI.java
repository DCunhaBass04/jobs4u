package jobs4u.app.backoffice.console.presentation.operator;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.core.jobapplication.application.RegisterJobOpeningApplicationController;
import jobs4u.core.jobopening.domain.JobOpening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterJobOpeningApplicationUI extends AbstractUI {
    @Autowired
    private RegisterJobOpeningApplicationController controller;

    @Override
    protected boolean doShow() {
        SelectWidget<JobOpening> jobOpeningWidget;
        SelectWidget<Integer> jobApplicationWidget;
        try {
            jobOpeningWidget = new SelectWidget<>("Select job opening", controller.getJobOpeningsToSelect());
            jobOpeningWidget.show();
            JobOpening opening = jobOpeningWidget.selectedElement();
            jobApplicationWidget = new SelectWidget<>("Select job application", controller.getApplicationsToSelect(opening));
            jobApplicationWidget.show();
            controller.registerJobApplication(jobApplicationWidget.selectedElement(), opening);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        return false;
    }
    @Override
    public String headline() {
        return "Register a job opening application";
    }
}
