package jobs4u.app.backoffice.console.presentation.admin.menu;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.app.backoffice.console.presentation.admin.DisableBackofficeUserUI;
import jobs4u.app.backoffice.console.presentation.admin.ListBackofficeUsersUI;
import jobs4u.app.backoffice.console.presentation.admin.RegisterBackofficeUserUI;
import jobs4u.infrastructure.auth.ui.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMenu extends AbstractUI {

    @Autowired
    private Menu adminMenu;

    @Autowired
    private DisableBackofficeUserUI disableBackofficeUserUI;

    @Autowired
    private ListBackofficeUsersUI listBackofficeUsersUI;

    @Autowired
    private RegisterBackofficeUserUI registerBackofficeUserUI;

    @Override
    protected boolean doShow() {
        buildMenu();
        adminMenu.run();
        return true;
    }

    @Override
    public String headline() {
        return "Admin menu";
    }

    private void buildMenu() {
        adminMenu.addOption(disableBackofficeUserUI);
        adminMenu.addOption(listBackofficeUsersUI);
        adminMenu.addOption(registerBackofficeUserUI);
    }

}
