package jobs4u.core.backoffice.application;
import eapli.framework.application.UseCaseController;
import jakarta.transaction.Transactional;
import jobs4u.core.jobapplication.domain.Interview;
import jobs4u.core.jobapplication.domain.InterviewAnswers;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.JobReference;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

@Component
@UseCaseController
public class UploadInterviewAnswersController {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired JobApplicationRepository jobApplicationRepository;

    private String FILEPATH = "../../../../../../applicationfiles"; //needs to be tested

    public void jobOpeningExists(String reference) {
        boolean exists = false;
        for (int i = 0; i < jobOpeningRepository.findAll().size(); i++) {
            if (jobOpeningRepository.findAll().get(i).getJobReference() == JobReference.valueOf(reference)){
                exists = true;
                break;
            }
        }
        if (!exists){
            throw new IllegalArgumentException("\nThe selected Job Opening does not exist, try again.");
        }
    }

    public void jobOpeningIsOfTheCustomerManager(List<JobOpening> jobOpeningList, String jobOpening){
        boolean valid = false;
        for (int i = 0; i < jobOpeningList.size(); i++) {
            if (jobOpeningList.get(i).getJobReference() == JobReference.valueOf(jobOpening)){
                valid = true;
                break;
            }
        }
        if (!valid){
            throw new IllegalArgumentException("\nThe selected Job Opening is not associated with you, try again.");
        }
    }

    public JobOpening findJobOpening(String jobOpeningReference) {
        return jobOpeningRepository.findByJobReference(JobReference.valueOf(jobOpeningReference));
    }

    public List<JobApplication> getApplicationList(JobOpening jobOpening){
        return jobApplicationRepository.findJobApplicationsByJobOpening(jobOpening);
    }

    @Transactional
    public void uploadFile(String fileName, JobApplication jobApplication) throws IOException {
        String completeFilePath = FILEPATH+"/"+fileName;
        String content = new String(Files.readAllBytes(Paths.get(completeFilePath)));
        Interview interview = jobApplication.getInterview();
        InterviewAnswers interviewAnswers = interview.getInterviewAnswers();
        interviewAnswers.setInterviewAnswers(content);
        interview.setInterviewAnswers(interviewAnswers);
        jobApplication.setInterview(interview);
        jobApplicationRepository.save(jobApplication);
    }
}
