package jobs4u.core.jobapplication.domain;

import eapli.framework.application.UseCaseController;
import jakarta.transaction.Transactional;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.RecruitmentPhase;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UseCaseController
public class RecordTimeDateInterviewController {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private Jobs4uUserAuthService authService;

    public List<JobOpening> getJobOpeningsWithAllPhases(){return jobOpeningRepository.findJobOpeningsOfCustomerManagerWithAllPhases(authService.authenticatedUser().getEmail());}

    public LocalDate getStartDate(JobOpening jobOpening){
        List<RecruitmentPhase> recruitmentPhases = jobOpening.getRecruitmentProcess().getRecruitmentPhases();
        RecruitmentPhase recruitmentPhase = recruitmentPhases.get(2);
        return recruitmentPhase.getStartDate();
    }

    public LocalDate getClosedDate(JobOpening jobOpening){
        List<RecruitmentPhase> recruitmentPhases = jobOpening.getRecruitmentProcess().getRecruitmentPhases();
        RecruitmentPhase recruitmentPhase = recruitmentPhases.get(2);
        return recruitmentPhase.getCloseDate();
    }

    public List<JobApplication> getJobApplicationsAccepted(JobOpening jobOpening){return jobApplicationRepository.findApplicationsInCertainPhase(ApplicationStateIndicator.ACCEPTED, jobOpening);}

    @Transactional
    public void recordInterviewDateTime(int year, int month, int day, int hour, int minutes, JobApplication jobApplication, LocalDate startDate, LocalDate closedDate) {
        jobApplication.recordTimeDateInterview(year, month, day, hour, minutes, startDate, closedDate);
        jobApplicationRepository.save(jobApplication);
    }
 }
