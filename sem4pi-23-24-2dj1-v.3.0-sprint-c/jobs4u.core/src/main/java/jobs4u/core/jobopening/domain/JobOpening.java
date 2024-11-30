package jobs4u.core.jobopening.domain;

import com.google.common.primitives.Longs;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.interviewmodel.domain.InterviewModel;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobopening.dto.JobOpeningSmallDTO;
import jobs4u.core.jobopening.services.JobReferenceGenerator;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import lombok.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import javax.validation.constraints.Min;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "JOB_OPENING")
public class JobOpening implements AggregateRoot<JobReference> {

    @EmbeddedId
    @Column(name = "JOB_REFERENCE")
    private JobReference jobReference;

    @ManyToOne
    @JoinColumn(name = "INTERVIEW_MODEL_ID")
    private InterviewModel interviewModel;

    @ManyToOne
    @JoinColumn(name = "JOB_REQUIREMENTS_DESIGNATION", referencedColumnName = "DESIGNATION")
    private JobRequirements jobRequirements;

    @OneToMany
    private List<Rank> rankList;

    @Embedded
    private RecruitmentProcess recruitmentProcess;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @Embedded
    private JobOpeningFunction jobOpeningFunction;

    @Enumerated(EnumType.STRING)
    private JobOpeningMode jobOpeningMode;

    @Embedded
    private JobOpeningAddress jobOpeningAddress;

    @ManyToOne
    @JoinColumn(name = "JOB_OPENING_CUSTOMER")
    private Customer customer;

    @Embedded
    private Vacancies numberOfVacancies;

    private LocalDateTime activeSince;

    private boolean isActive;

    @Override
    public boolean sameAs(Object other) {return this.jobReference.equals(((JobOpening)other).jobReference);}

    @Override
    public JobReference identity() {return jobReference;}

    // Constructor for testing purposes
    public JobOpening(RecruitmentProcess rp) {
        this.recruitmentProcess = rp;
        this.isActive = true;
    }


    public JobOpening(ContractType contractType, JobOpeningFunction jobOpeningFunction, JobOpeningMode jobOpeningMode, JobOpeningAddress jobOpeningAddress, Customer customer, Vacancies numberOfVacancies, LocalDateTime activeSince, Long jobReferenceNumber) {

        Preconditions.noneNull(contractType, jobOpeningFunction, jobOpeningMode, jobOpeningAddress, customer, numberOfVacancies, activeSince);

        jobReference = JobReferenceGenerator.generateJobReference(customer, jobReferenceNumber);
        this.contractType = contractType;
        this.jobOpeningFunction = jobOpeningFunction;
        this.jobOpeningMode = jobOpeningMode;
        this.jobOpeningAddress = jobOpeningAddress;
        this.customer = customer;
        this.numberOfVacancies = numberOfVacancies;
        this.activeSince = activeSince;
        this.isActive = true;
    }

    public void evaluteGrades(List<JobApplication> applications) throws MalformedURLException, URISyntaxException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(recruitmentProcess.getCurrentPhase().designation() !=RecruitmentPhaseDesignation.INTERVIEWS)
            throw new IllegalStateException("Cannot do this process outside of Interviews phase");

        Class<?> cls = interviewModel.getClassFromPlugin();
        Method method = cls.getDeclaredMethod("evaluateTextFile", String.class);
        Object instance = cls.getConstructor().newInstance();
        for(JobApplication application : applications){
            String runResult = method.invoke(instance, application.getInterview().getInterviewAnswers().toString()).toString();
            application.updateGrade(runResult);
        }
    }




    public void executeRequirements(List<JobApplication> applications) throws MalformedURLException, URISyntaxException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(recruitmentProcess.getCurrentPhase().designation() !=RecruitmentPhaseDesignation.SCREENING)
            throw new IllegalStateException("Cannot do this process outside of Screening phase");
        Class<?> cls = jobRequirements.getClassFromPlugin();
        Method method = cls.getDeclaredMethod("evaluateTextFile", String.class);
        Object instance = cls.getConstructor().newInstance();
        for(JobApplication application : applications){
            if(application.getJobOpening().sameAs(this)) {
                String runResult = method.invoke(instance, application.getJobRequirementsAnswers()).toString();
                application.updateStatus(runResult);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Job Opening %s:%n Contract Type = %s%n Function = %s%n Mode = %s%n Address = %s%n Customer = %s%n Number of Vacancies = %s%n Active since = %04d-%02d-%02d %02d:%02d%n Interview Model = %s%n Requirements used = %s%n", jobReference, contractType, jobOpeningFunction, jobOpeningMode, jobOpeningAddress, customer.getCustomerCode(), numberOfVacancies, activeSince.getYear(), activeSince.getMonthValue(), activeSince.getDayOfMonth(), activeSince.getHour(), activeSince.getMinute(), interviewModel, jobRequirements);
    }


    public void verifyIfIsInIterviewPhaseTimeframe(LocalDate localDate, LocalDate startDate, LocalDate closeDate) {
        if (localDate.isBefore(startDate) || localDate.isAfter(closeDate)) {
            throw new DateTimeException("The date " + localDate + " is outside the allowed range: "
                    + startDate + " to " + closeDate);
        }
    }



    public void isOnCorrectPhases() {
        if((this.getRecruitmentProcess() != null) && (this.getRecruitmentProcess().getCurrentPhase() != null)) {
            if (this.getRecruitmentProcess().getCurrentPhase().getRecruitmentPhaseDesignation() == RecruitmentPhaseDesignation.SCREENING ||
                    this.getRecruitmentProcess().getCurrentPhase().getRecruitmentPhaseDesignation() == RecruitmentPhaseDesignation.ANALYSIS ||
                    this.getRecruitmentProcess().getCurrentPhase().getRecruitmentPhaseDesignation() == RecruitmentPhaseDesignation.INTERVIEWS ||
                    this.getRecruitmentProcess().getCurrentPhase().getRecruitmentPhaseDesignation() == RecruitmentPhaseDesignation.RESULT)
                throw new RuntimeException ("Cannot edit Job Opening:\n Current Job Opening Phase is invalid");

        }
    }


    public void closeJobOpening() {
        this.isActive = false;
    }
    public JobOpeningSmallDTO toSmallDto(long numApplicants){
        return new JobOpeningSmallDTO(jobReference.getJobReference()+'0',
                jobOpeningFunction.getJobOpeningFunction()+'0', activeSince, numApplicants);
    }
}
