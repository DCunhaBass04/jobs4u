package jobs4u.core.backoffice.application;

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
public class EveluateApplicationInterviewGradesController {

    @Autowired
    private Jobs4uUserAuthService authService;
    @Autowired
    private JobOpeningRepository jobOpeningRepository;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;


    public List<JobOpening> getOpeningsInInterviewsPhase() {
        return jobOpeningRepository.findJobOpeningsFromCustomerManagerInCertainPhase(RecruitmentPhaseDesignation.INTERVIEWS, authService.authenticatedUser().getEmail());
    }

    private List<JobApplication> getApplicationsWithInterviewModels(JobOpening jobOpening) {
        return jobApplicationRepository.findApplicationsFromOpeningWithInterviewModels(jobOpening);
    }

    @Transactional
    public void executeProcess(JobOpening opening) throws MalformedURLException, URISyntaxException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        opening.evaluteGrades(getApplicationsWithInterviewModels(opening));
        jobApplicationRepository.saveAll(getApplicationsWithInterviewModels(opening));
    }
}



