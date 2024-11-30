package jobs4u.core.backoffice.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.RecruitmentPhaseDesignation;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@Component
@UseCaseController
public class VerifyApplicationRequirementsController {
    @Autowired
    private Jobs4uUserAuthService authService;
    @Autowired
    private JobOpeningRepository jobOpeningRepository;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public List<JobOpening> getOpeningsInInterviewPhase() {
        return jobOpeningRepository.findJobOpeningsFromCustomerManagerInCertainPhase(RecruitmentPhaseDesignation.SCREENING, authService.authenticatedUser().getEmail());
    }

    private List<JobApplication> getApplicationsWithRequirementAnswers(JobOpening opening) {
        return jobApplicationRepository.findApplicationsFromOpeningWithRequirementAnswers(opening);
    }

    @Transactional
    public void executeProcess(JobOpening opening) throws MalformedURLException, URISyntaxException,
            ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        List<JobApplication> list = getApplicationsWithRequirementAnswers(opening);
        opening.executeRequirements(list);
        jobApplicationRepository.saveAll(list);
    }
}
