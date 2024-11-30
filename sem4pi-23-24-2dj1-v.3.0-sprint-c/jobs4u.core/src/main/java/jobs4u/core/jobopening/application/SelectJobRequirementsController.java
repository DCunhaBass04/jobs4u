package jobs4u.core.jobopening.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import jobs4u.core.jobrequirements.repositories.JobRequirementsRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@UseCaseController
public class SelectJobRequirementsController {

    @Autowired
    private Jobs4uUserAuthService authService;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;
    @Autowired
    private JobRequirementsRepository jobRequirementsRepository;
    public List<JobOpening> findJobOpeningsByCustomerManagerWithoutJobRequirement(){return jobOpeningRepository.findJobOpeningsOfCustomerManagerWOJobRequirements(authService.authenticatedUser().getEmail());}
    public List<JobRequirements> findJobRequirements(){return jobRequirementsRepository.findAll();}
    public void saveJobOpeningWithRequirement(JobOpening jobOpening, JobRequirements jobRequirements){
        jobOpening.setJobRequirements(jobRequirements);
        jobOpeningRepository.save(jobOpening);
    }
}


