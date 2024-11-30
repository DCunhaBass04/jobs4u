package jobs4u.app.backoffice.console.presentation.customermanager.menu;

import eapli.framework.presentation.console.AbstractUI;

import jobs4u.app.backoffice.console.presentation.customermanager.*;
import jobs4u.infrastructure.auth.ui.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerManagerMenu extends AbstractUI {

    @Autowired
    private Menu customerManagerMenu;

    @Autowired
    private DisplayCandidateApplicationsUI displayCandidateApplicationsUI;

    @Autowired
    private DisplayCandidatePersonalDataUI displayCandidatePersonalDataUI;

    @Autowired
    private ExportInterviewTemplateFileUI exportInterviewTemplateFileUI;

    @Autowired
    private ListJobOpeningApplicationsUI listJobOpeningApplicationsUI;

    @Autowired
    private ListJobOpeningsUI listJobOpeningsUI;

    @Autowired
    private RegisterCustomerUI registerCustomerUI;

    @Autowired
    private RegisterJobOpeningUI registerJobOpeningUI;

    @Autowired
    private SelectJobOpeningInterviewModelUI selectJobOpeningInterviewModelUI;

    @Autowired
    private SelectJobOpeningRequirementsUI selectJobOpeningRequirmentsUI;
    @Autowired
    private EditJobOpeningUI editJobOpeningUI;

    @Autowired
    private SetupJobOpeningRecruitmentPhasesUI setupJobOpeningRecruitmentPhasesUI;

    @Autowired
    private DisplayAllApplicationDataUI displayAllApplicationDataUI;

    @Autowired
    private OpenCloseJobOpeningPhaseUI openCloseJobOpeningPhaseUI;

    @Autowired
    private RankCandidatesUI rankCandidatesUI;
    @Autowired
    private EveluateApplicationInterviewGradesUI eveluateApplicationInterviewGradesUI;
    @Autowired
    private VerifyApplicationRequirementsUI verifyApplicationRequirementsUI;

    @Autowired
    private RecordTimeDateInterviewUI recordTimeDateInterviewUI;

    @Override
    protected boolean doShow() {
        buildMenu();
        customerManagerMenu.run();
        return true;
    }

    @Override
    public String headline() {
        return "Customer manager";
    }

    private void buildMenu() {
        customerManagerMenu.addOption(displayCandidateApplicationsUI);
        customerManagerMenu.addOption(displayCandidatePersonalDataUI);
        customerManagerMenu.addOption(exportInterviewTemplateFileUI);
        customerManagerMenu.addOption(listJobOpeningApplicationsUI);
        customerManagerMenu.addOption(listJobOpeningsUI);
        customerManagerMenu.addOption(registerCustomerUI);
        customerManagerMenu.addOption(registerJobOpeningUI);
        customerManagerMenu.addOption(selectJobOpeningInterviewModelUI);
        customerManagerMenu.addOption(selectJobOpeningRequirmentsUI);
        customerManagerMenu.addOption(editJobOpeningUI);
        customerManagerMenu.addOption(setupJobOpeningRecruitmentPhasesUI);
        customerManagerMenu.addOption(displayAllApplicationDataUI);
        customerManagerMenu.addOption(openCloseJobOpeningPhaseUI);
        customerManagerMenu.addOption(rankCandidatesUI);
        customerManagerMenu.addOption(eveluateApplicationInterviewGradesUI);
        customerManagerMenu.addOption(verifyApplicationRequirementsUI);
        customerManagerMenu.addOption(recordTimeDateInterviewUI);
    }
}
