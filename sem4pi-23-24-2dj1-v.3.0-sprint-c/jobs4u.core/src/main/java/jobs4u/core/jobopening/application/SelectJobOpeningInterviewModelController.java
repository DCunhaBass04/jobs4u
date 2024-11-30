package jobs4u.core.jobopening.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.interviewmodel.domain.InterviewModel;
import jobs4u.core.interviewmodel.domain.InterviewModelDesignation;
import jobs4u.core.interviewmodel.repositories.InterviewModelRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.JobReference;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type Select job opening interview model controller.
 */
@Component
@UseCaseController
public class SelectJobOpeningInterviewModelController {
    @Autowired
    private Jobs4uUserAuthService authService;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private InterviewModelRepository interviewModelRepository;

    /**
     * Sets interview model for job opening.
     *
     * @param jobReference the job reference
     * @param designation  the designation
     */
    public void setInterviewModelForJobOpening(JobReference jobReference, InterviewModelDesignation designation) {
        JobOpening jobOpening = jobOpeningRepository.findByJobReference(jobReference);
        InterviewModel interviewModel = interviewModelRepository.findInterviewModelByInterviewModelDesignation(designation);
        jobOpening.setInterviewModel(interviewModel);
        jobOpeningRepository.save(jobOpening);
    }

    /**
     * Find customer manager job openings list by users email.
     *
     * @return the list
     */
    public List<JobOpening> findCustomerManagerJobOpenings() {
        return jobOpeningRepository.findJobOpeningsOfCustomerManager(authService.authenticatedUser().getEmail());
    }

    public List<InterviewModel> findInterviewModels() {
        return interviewModelRepository.findAll();
    }
}
