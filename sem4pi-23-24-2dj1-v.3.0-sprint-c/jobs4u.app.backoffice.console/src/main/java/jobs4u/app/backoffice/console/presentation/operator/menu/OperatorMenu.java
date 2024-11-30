package jobs4u.app.backoffice.console.presentation.operator.menu;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.app.backoffice.console.presentation.operator.*;
import jobs4u.infrastructure.auth.ui.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperatorMenu extends AbstractUI {

    @Autowired
    private Menu operatorMenu;

    @Autowired
    private ExportRequirementsTemplateFileUI exportRequirementsTemplateFileUI;

    @Autowired
    private ListCandidatesUI listCandidatesUI;

    @Autowired
    private RegisterCandidateUI registerCandidateUI;

    @Autowired
    private RegisterJobOpeningApplicationUI registerJobOpeningApplicationUI;

    @Autowired
    private DisableCandidateUI disableCandidateUI;

    @Autowired
    private UploadRequirementsUI uploadRequirementsUI;
    @Override
    protected boolean doShow() {
        buildMenu();
        operatorMenu.run();
        return true;
    }

    @Override
    public String headline() {
        return "Operator";
    }

    private void buildMenu() {
        operatorMenu.addOption(exportRequirementsTemplateFileUI);
        operatorMenu.addOption(listCandidatesUI);
        operatorMenu.addOption(registerCandidateUI);
        operatorMenu.addOption(registerJobOpeningApplicationUI);
        operatorMenu.addOption(disableCandidateUI);
        operatorMenu.addOption(uploadRequirementsUI);
    }
}