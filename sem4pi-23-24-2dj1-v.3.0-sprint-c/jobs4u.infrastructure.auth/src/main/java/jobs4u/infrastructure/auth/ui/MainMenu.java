package jobs4u.infrastructure.auth.ui;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.infrastructure.auth.ui.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenu extends AbstractUI {

    @Autowired
    private Menu mainMenu;

    @Autowired
    private LoginUI loginUI;

    @Override
    protected boolean doShow() {
        buildMenu();
        mainMenu.run();
        return true;
    }

    @Override
    public String headline() {
        return "Main menu";
    }

    private void buildMenu() {
        mainMenu.addOption(loginUI);
    }
}
