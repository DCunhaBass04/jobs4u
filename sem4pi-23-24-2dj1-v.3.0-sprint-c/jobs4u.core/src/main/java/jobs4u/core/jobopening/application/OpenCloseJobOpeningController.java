package jobs4u.core.jobopening.application;

import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.core.jobopening.services.RecruitmentProcessManager;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenCloseJobOpeningController {

    @Autowired
    RecruitmentProcessManager recruitmentProcessManager;

    @Autowired
    JobOpeningRepository jobOpeningRepository;

    @Autowired
    Jobs4uUserAuthService authService;

    public List<JobOpening> getJobOpenings() {
        return jobOpeningRepository.findJobOpeningsOfCustomerManager(authService.authenticatedUser().getEmail());
    }

    public boolean advancePhase(JobOpening jobOpening) {
        return recruitmentProcessManager.advancePhase(jobOpening);
    }

    public boolean retrocedePhase(JobOpening jobOpening) {
        return recruitmentProcessManager.retrocedePhase(jobOpening);
    }

}
