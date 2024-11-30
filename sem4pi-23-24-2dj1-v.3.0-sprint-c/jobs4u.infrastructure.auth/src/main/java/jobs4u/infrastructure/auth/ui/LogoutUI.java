package jobs4u.infrastructure.auth.ui;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.infrastructure.auth.controller.LogoutController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutUI extends AbstractUI {

    @Autowired
    private LogoutController controller;

    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return "Logout";
    }

}
