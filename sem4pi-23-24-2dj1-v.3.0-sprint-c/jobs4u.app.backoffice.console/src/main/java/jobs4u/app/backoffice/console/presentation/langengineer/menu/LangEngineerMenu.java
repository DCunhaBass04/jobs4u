package jobs4u.app.backoffice.console.presentation.langengineer.menu;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.app.backoffice.console.presentation.langengineer.DeployInterviewModelPluginUI;
import jobs4u.app.backoffice.console.presentation.langengineer.DeployRequirementsPluginUI;
import jobs4u.infrastructure.auth.ui.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LangEngineerMenu extends AbstractUI {

    @Autowired
    private Menu langEngineerMenu;

    @Autowired
    private DeployInterviewModelPluginUI deployInterviewModelPluginUI;

    @Autowired
    private DeployRequirementsPluginUI deployRequirementsPluginUI;

    @Override
    protected boolean doShow() {
        buildMenu();
        langEngineerMenu.run();
        return true;
    }

    @Override
    public String headline() {
        return "Language engineer";
    }

    private void buildMenu() {
        langEngineerMenu.addOption(deployInterviewModelPluginUI);
        langEngineerMenu.addOption(deployRequirementsPluginUI);
    }
}
