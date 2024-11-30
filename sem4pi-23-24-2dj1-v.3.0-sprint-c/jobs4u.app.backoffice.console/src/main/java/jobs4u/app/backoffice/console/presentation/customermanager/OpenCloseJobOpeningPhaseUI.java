package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.core.jobopening.application.OpenCloseJobOpeningController;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.RecruitmentPhase;
import jobs4u.core.jobopening.domain.RecruitmentPhaseState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenCloseJobOpeningPhaseUI extends AbstractUI {

    @Autowired
    private OpenCloseJobOpeningController ctrl;

    @Override
    protected boolean doShow() {
        SelectWidget<JobOpening> jobOpeningSelectWidget = new SelectWidget<>("Select job opening", ctrl.getJobOpenings());
        jobOpeningSelectWidget.show();
        JobOpening selected = jobOpeningSelectWidget.selectedElement();
        List<RecruitmentPhase> recruitmentPhases = selected.getRecruitmentProcess().getRecruitmentPhases();
        List<RecruitmentPhase> activePhase = new ArrayList<>();
        List<RecruitmentPhase> openedPhase;
        for (RecruitmentPhase rp : recruitmentPhases) {
            if (rp.getRecruitmentPhaseState().equals(RecruitmentPhaseState.ACTIVE)) {
                activePhase.add(rp);
            }
        }

        SelectWidget<RecruitmentPhase> recruitmentPhaseWidget = new SelectWidget<>("Select recruitment phase", activePhase);
        recruitmentPhaseWidget.show();
        ctrl.advancePhase(jobOpeningSelectWidget.selectedElement());
        return false;
    }

    @Override
    public String headline() {
        return "Open/close job opening phase";
    }
}
