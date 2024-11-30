package jobs4u.core.jobapplication.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.backoffice.services.FileAnalyzer;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.candidate.repositories.CandidateRepository;
import jobs4u.core.jobapplication.domain.*;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.JobReference;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

@Component
@UseCaseController
public class RegisterJobOpeningApplicationController {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Transactional
    public void registerJobApplication(int id, JobOpening jobOpening) throws IOException {
        String filePath = getFilePathFromProperties();
        EmailAddress emailAddress = FileAnalyzer.getCandidateEmail(String.format("%s/%s/%s/%s", filePath, jobOpening.getJobReference().toString(), id, String.format("%d-candidate-data.txt", id)));
        Candidate candidate = candidateRepository.findCandidateByEmailAddress(emailAddress);
        JobApplicationBuilder jobApplicationBuilder = new JobApplicationBuilder();
        ApplicationState applicationState = new ApplicationState(ApplicationStateIndicator.PENDING, null);
        JobApplication jobApplication = jobApplicationBuilder.withId(id).forJobOpening(jobOpening).ofCandidate(candidate).withFiles(filePath).withState(applicationState).build();
        jobApplicationRepository.save(jobApplication);
    }

    public List<JobOpening> getJobOpeningsToSelect() throws IOException {
        List<JobOpening> openings = new ArrayList<>();
        String filePath = getFilePathFromProperties();
        List<String> dirs = Stream.of(Objects.requireNonNull(new File(filePath).listFiles()))
                .filter(File::isDirectory) //Only get directories
                .map(File::getName) //Get names
                .toList(); //Send them to List
        for(String dir : dirs) openings.add(jobOpeningRepository.findByJobReference(JobReference.valueOf(dir)));
        return openings;
    }
    public List<Integer> getApplicationsToSelect(JobOpening opening) throws IOException {
        List<Integer> applications = new ArrayList<>();
        String filePath = String.format("%s/%s", getFilePathFromProperties(), opening.getJobReference().toString());
        List<String> dirs = Stream.of(Objects.requireNonNull(new File(filePath).listFiles()))
                .filter(File::isDirectory) //Only get directories
                .map(File::getName) //Get names
                .toList(); //Send them to List
        for(String dir : dirs) applications.add(Integer.valueOf(dir));
        return applications;
    }
    private String getFilePathFromProperties() throws IOException {
        Properties appProps = new Properties();
        appProps.load(new FileReader("applicationsfilelocation.properties"));
        return appProps.getProperty("file-bot-directory");
    }
}
