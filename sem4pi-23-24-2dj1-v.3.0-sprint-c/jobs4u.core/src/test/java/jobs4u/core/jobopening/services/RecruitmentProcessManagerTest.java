package jobs4u.core.jobopening.services;

import jobs4u.core.jobopening.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecruitmentProcessManagerTest {


    private RecruitmentProcess getTestRecruitmentProcess() {
        List<RecruitmentPhase> recruitmentPhaseList = new ArrayList<>();
        recruitmentPhaseList.add(new RecruitmentPhase(RecruitmentPhaseDesignation.APPLICATION,
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2000, 1, 2)));
        recruitmentPhaseList.add(new RecruitmentPhase(RecruitmentPhaseDesignation.SCREENING,
                LocalDate.of(2000, 2, 1),
                LocalDate.of(2000, 2, 2)));
        recruitmentPhaseList.add(new RecruitmentPhase(RecruitmentPhaseDesignation.INTERVIEWS,
                LocalDate.of(2000, 3, 1),
                LocalDate.of(2000, 3, 2)));
        recruitmentPhaseList.add(new RecruitmentPhase(RecruitmentPhaseDesignation.ANALYSIS,
                LocalDate.of(2000, 4, 1),
                LocalDate.of(2000, 4, 2)));
        recruitmentPhaseList.add(new RecruitmentPhase(RecruitmentPhaseDesignation.RESULT,
                LocalDate.of(2000, 5, 1),
                LocalDate.of(2000, 5, 2)));
        return new RecruitmentProcess(recruitmentPhaseList);
    }

    @Test
    void ensureClosedPhaseOpensNextOne() {
        RecruitmentProcess rp = getTestRecruitmentProcess();
        JobOpening jo = new JobOpening(rp);
        RecruitmentProcessManager rpm = new RecruitmentProcessManager();
        rpm.advancePhase(jo);
        Assertions.assertTrue(rp.getCurrentPhase().getRecruitmentPhaseState().equals(RecruitmentPhaseState.OPEN));
    }

    @Test
    void ensureImpossibleRetrocedeWhenActive() {
        RecruitmentProcess rp = getTestRecruitmentProcess();
        RecruitmentProcessManager rpm = new RecruitmentProcessManager();
        JobOpening jo = new JobOpening(rp);
        rpm.advancePhase(jo);
        rp.getCurrentPhase().setRecruitmentPhaseState(RecruitmentPhaseState.ACTIVE);
        Assertions.assertFalse(rpm.retrocedePhase(jo));
    }

    @Test
    void ensureJobOpeningClosed() {
        RecruitmentProcess rp = getTestRecruitmentProcess();
        RecruitmentProcessManager rpm = new RecruitmentProcessManager();
        JobOpening jo = new JobOpening(rp);
        rp.setCurrentPhase(new RecruitmentPhase(RecruitmentPhaseDesignation.RESULT, LocalDate.of(2000, 5, 1),
                LocalDate.of(2000, 5, 2)));
        rpm.advancePhase(jo);
        Assertions.assertFalse(jo.isActive());

    }

    @Test
    void advancePhase() {

    }

    @Test
    void retrocedePhase() {

    }
}