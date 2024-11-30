package jobs4u.core.jobopening.services;

import jobs4u.core.jobapplication.domain.ApplicationStateIndicator;
import jobs4u.core.jobapplication.domain.Interview;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.*;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruitmentProcessManager {

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    public boolean advancePhase(JobOpening jobOpening) {
        RecruitmentProcess rp = jobOpening.getRecruitmentProcess();
        RecruitmentPhase currentPhase = rp.getCurrentPhase();
        if (!jobOpening.isActive()) {
            throw new IllegalStateException("The recruitment process has concluded, cannot advance phase.");
        }
        if (validatePhaseAdvancement(jobOpening, currentPhase)) {
            currentPhase.setRecruitmentPhaseState(RecruitmentPhaseState.CLOSED);
            int nextIndex = rp.getRecruitmentPhases().indexOf(currentPhase) + 1;
            rp.setCurrentPhase(rp.getRecruitmentPhases().get(nextIndex));
            currentPhase.setRecruitmentPhaseState(RecruitmentPhaseState.OPEN);
            return true;
        }
        return false;
    }

    public boolean retrocedePhase(JobOpening jobOpening) {
        RecruitmentProcess rp = jobOpening.getRecruitmentProcess();
        RecruitmentPhase currentPhase = rp.getCurrentPhase();
        if (currentPhase.getRecruitmentPhaseState().equals(RecruitmentPhaseState.ACTIVE) ||
                currentPhase.getRecruitmentPhaseState().equals(RecruitmentPhaseState.CLOSED) ||
                currentPhase.getRecruitmentPhaseDesignation().equals(RecruitmentPhaseDesignation.APPLICATION)) {
            return false;
        }
        int previousIndex = rp.getRecruitmentPhases().indexOf(currentPhase) - 1;
        currentPhase.setRecruitmentPhaseState(RecruitmentPhaseState.UNNACTIVE);
        rp.setCurrentPhase(rp.getRecruitmentPhases().get(previousIndex));
        return true;
    }

    private boolean validatePhaseAdvancement(JobOpening jobOpening, RecruitmentPhase currentPhase) {
        switch (currentPhase.getRecruitmentPhaseDesignation()) {
            case APPLICATION -> {
                return true;
            }
            case SCREENING -> {
                return verifyScreeningPhase(jobOpening);
            }
            case INTERVIEWS -> {
                return verifyInterviewsPhase(jobOpening);
            }
            case ANALYSIS -> {
                return verifyAnalysisPhase(jobOpening);
            }
            case RESULT -> {
                jobOpening.closeJobOpening();
                return true;
            }
        }
        return false;
    }

    private boolean verifyScreeningPhase(JobOpening jobOpening) {
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationsByJobOpening(jobOpening);
        for (JobApplication jobApplication : jobApplications) {
            if (jobApplication.getApplicationState().getState().equals(ApplicationStateIndicator.PENDING)) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyInterviewsPhase(JobOpening jobOpening) {
        List<JobApplication> jobApplications = jobApplicationRepository.findJobApplicationsByJobOpening(jobOpening);
        for (JobApplication jo : jobApplications) {
            if (jo.getInterview() == null) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyAnalysisPhase(JobOpening jobOpening) {
        return !jobOpening.getRankList().isEmpty();
    }

}
