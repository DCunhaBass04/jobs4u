package jobs4u.core.jobopening.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.RecruitmentPhase;
import jobs4u.core.jobopening.domain.RecruitmentProcess;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UseCaseController
public class SetupJobOpeningRecuitmentPhasesController {
    @Autowired
    private Jobs4uUserAuthService authService;
    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    public List<JobOpening> findCustomerManagerJobOpeningsNoRecruitmentProcess() {
        return jobOpeningRepository.findJobOpeningsOfCustomerManagerNoRecruitmentProcess(authService.authenticatedUser().getEmail());
    }
    public void updateJobOpening(JobOpening jobOpening, List<RecruitmentPhase> phases) {
        jobOpening.setRecruitmentProcess(new RecruitmentProcess(phases));
        jobOpeningRepository.save(jobOpening);
    }
}
