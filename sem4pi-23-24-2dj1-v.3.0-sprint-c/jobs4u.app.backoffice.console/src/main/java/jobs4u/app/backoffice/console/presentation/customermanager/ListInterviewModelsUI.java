package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.interviewmodel.application.ListInterviewModelsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListInterviewModelsUI extends AbstractUI {

    @Autowired
    private ListInterviewModelsController controller;

    @Override
    protected boolean doShow() {
        controller.findAll().forEach(System.out::println);
        return true;
    }

    @Override
    public String headline() {
        return null;
    }
}
