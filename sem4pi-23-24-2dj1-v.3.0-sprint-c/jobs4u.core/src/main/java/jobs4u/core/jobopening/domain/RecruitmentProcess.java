package jobs4u.core.jobopening.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.lang.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentProcess {

    @Getter
    @ElementCollection(targetClass = RecruitmentPhase.class, fetch = FetchType.EAGER)
    private List<RecruitmentPhase> recruitmentPhases;

    @Embedded
    private RecruitmentPhase currentPhase;

    public RecruitmentProcess(List<RecruitmentPhase> recruitmentPhases) {
        this.recruitmentPhases = recruitmentPhases;
        this.currentPhase = recruitmentPhases.get(0);
        currentPhase.setRecruitmentPhaseState(RecruitmentPhaseState.OPEN);
    }


    public boolean verifyPhaseDates(List<RecruitmentPhase> recruitmentPhases) {
        if (recruitmentPhases.size() < 4 || recruitmentPhases.size() > 5) return false;

        List<RecruitmentPhaseDesignation> expectedOrder = new ArrayList<>();
        expectedOrder.add(RecruitmentPhaseDesignation.APPLICATION);
        expectedOrder.add(RecruitmentPhaseDesignation.SCREENING);
        if (recruitmentPhases.size() == 5)
            expectedOrder.add(RecruitmentPhaseDesignation.INTERVIEWS);
        expectedOrder.add(RecruitmentPhaseDesignation.ANALYSIS);
        expectedOrder.add(RecruitmentPhaseDesignation.RESULT);

        Iterator<RecruitmentPhase> iterator = recruitmentPhases.iterator();
        LocalDate previousEnd = null;
        for (RecruitmentPhaseDesignation expectedName : expectedOrder) {
            if (!iterator.hasNext()) return false;
            RecruitmentPhase phase = iterator.next();
            if (phase.designation() != expectedName) return false;
            if (phase.startDate().isAfter(phase.closeDate())) return false;
            if (previousEnd != null && phase.startDate().isBefore(previousEnd)) return false;
            previousEnd = phase.closeDate();
        }
        return true;
    }

    public boolean hasInterview() {
        return recruitmentPhases.size() == 5;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder(String.format("Recruitment Phases:%n"));
        for(RecruitmentPhase phase : recruitmentPhases)
            builder.append(String.format("Phase %s%n", phase));
        return builder.toString();
    }

}
