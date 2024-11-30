package jobs4u.core.backoffice.application;

import eapli.framework.application.UseCaseController;
import jakarta.transaction.Transactional;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
@UseCaseController
public class UploadRequirementsController {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired JobApplicationRepository jobApplicationRepository;

    public List<JobApplication> getApplicationList(JobOpening jobOpening){
        return jobApplicationRepository.findJobApplicationsByJobOpening(jobOpening);
    }

    @Transactional
    public void uploadFile(String fileName, JobApplication jobApplication) throws IOException {
        String jobRequirementsAnswers = new String(Files.readAllBytes(Paths.get(fileName)));
        jobApplication.setJobRequirementsAnswers(jobRequirementsAnswers);
        jobApplicationRepository.save(jobApplication);
    }

    public List<JobOpening> getJobOpenings() {
        return jobOpeningRepository.findAll();
    }
}
