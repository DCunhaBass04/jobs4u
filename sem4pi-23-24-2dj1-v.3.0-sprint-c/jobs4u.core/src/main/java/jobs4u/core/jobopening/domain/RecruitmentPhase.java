package jobs4u.core.jobopening.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter()
@Table(name = "RECRUITMENT_PHASES")
public class RecruitmentPhase {

    @Enumerated(EnumType.STRING)
    private RecruitmentPhaseState recruitmentPhaseState;

    @Enumerated(EnumType.STRING)
    private RecruitmentPhaseDesignation recruitmentPhaseDesignation;

    @Temporal(value = TemporalType.DATE)
    private LocalDate startDate;

    @Temporal(value = TemporalType.DATE)
    private LocalDate closeDate;

    public RecruitmentPhaseDesignation designation() {
        return recruitmentPhaseDesignation;
    }

    public LocalDate startDate() {
        return startDate;
    }


    public LocalDate closeDate() {
        return closeDate;
    }

    protected RecruitmentPhase() {
    }

    /**
     * Instantiates a new Recruitment Phase.
     * <p>
     * This constructor initializes a recruitment phase with a designation, start date, and close date.
     * It validates the input parameters to ensure they are not null and that the start date is before the close date.
     *
     * @param recruitmentPhaseDesignation the designation of the recruitment phase to be assigned
     * @param startDate                   the start date of the recruitment phase; must be before the close date
     * @param closeDate                   the close date of the recruitment phase; must be after the start date
     * @throws IllegalStateException if any parameter is null or if the start date is not before the close date
     *                               <p>
     *                               Example of valid parameters:
     *                               RecruitmentPhaseDesignation designation = new RecruitmentPhaseDesignation("APPLICATION");
     *                               LocalDate startDate = LocalDate.of(2023, 1, 1);
     *                               LocalDate closeDate = LocalDate.of(2023, 3, 1);
     *                               new RecruitmentPhase(designation, startDate, closeDate);
     */
    public RecruitmentPhase(RecruitmentPhaseDesignation recruitmentPhaseDesignation, LocalDate startDate, LocalDate closeDate) {
        if (recruitmentPhaseDesignation == null || startDate == null || closeDate == null || startDate.isAfter(closeDate) || startDate.isEqual(closeDate))
            throw new IllegalStateException(
                    "Invalid Phases Desgination ,Start or End Date are not on valid format");
        this.recruitmentPhaseDesignation = recruitmentPhaseDesignation;
        this.startDate = startDate;
        this.closeDate = closeDate;
        this.recruitmentPhaseState = RecruitmentPhaseState.UNNACTIVE;
    }




    /**
        For Testing Purposes Only
     */
    protected RecruitmentPhase(RecruitmentPhaseDesignation recruitmentPhaseDesignation) {
        this.recruitmentPhaseDesignation = recruitmentPhaseDesignation;
    }



    @Override
    public String toString() {
        return "RecruitmentPhase{" +
                "recruitmentPhaseDesignation=" + recruitmentPhaseDesignation +
                ", startDate=" + startDate +
                ", closeDate=" + closeDate +
                '}';
    }


}
