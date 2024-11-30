package jobs4u.core.candidate.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@UseCaseController
public class DisplayCandidateApplicationsController {
    private final int NUM_WORDS = 20;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private Jobs4uUserAuthService authService;
    public List<JobApplication> findApplicationsByCandidate(Candidate candidate){
        return jobApplicationRepository.findApplicationsByCandidateFromCustomerManager(candidate, authService.authenticatedUser().getEmail());
    }
    public List<Map.Entry<String, Map.Entry<List<String>, Integer>>> createListOfTopWords(JobApplication jobApplication) throws InterruptedException, RuntimeException {
        return jobApplication.createListOfWords(NUM_WORDS);
    }
}
