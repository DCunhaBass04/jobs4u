package jobs4u.app.backoffice.console.presentation.admin;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.backoffice.application.ListBackofficeUsersController;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListBackofficeUsersUI extends AbstractUI {

    @Autowired
    private ListBackofficeUsersController controller;

    @Override
    protected boolean doShow() {
        List<Jobs4uUser> backofficeUsers = controller.getBackofficeUsers();
        backofficeUsers.forEach(System.out::println);
        return false;
    }

    @Override
    public String headline() {
        return "List backoffice users";
    }
}
