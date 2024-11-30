package jobs4u.infrastructure.bootstrappers;

import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.candidate.repositories.CandidateRepository;
import jobs4u.core.jobapplication.domain.*;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobReference;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
public class JobApplicationBootstrapper {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    public void bootstrap() throws IOException {
        Properties appProps = new Properties();
        appProps.load(new FileReader("applicationsfilelocation.properties"));
        String filePath = appProps.getProperty("file-bot-directory");
        JobApplicationBuilder builder = new JobApplicationBuilder();
        jobApplicationRepository.save(builder
                .withId(1)
                .forJobOpening(jobOpeningRepository.findByJobReference(new JobReference("IBM-1")))
                .ofCandidate(candidateRepository.findCandidateByEmailAddress(EmailAddress.valueOf("candidate1@jobs4u.com")))
                .withFiles(filePath)
                .withState(new ApplicationState(ApplicationStateIndicator.PENDING, null))
                .withAddonAnswers(String.format("# Enter the number of years of experience (integer)%n" +
                        "Experience-years: 10%n" +
                        "# Select one degree (None; Bachelor; Master; PhD)%n" +
                        "Academic-degree: Bachelor%n" +
                        "# Select one or more programming languages you are proficient in (java; javascript; python)%n" +
                        "Programming-languages: java, javascript, python%n"))
                .build());
        jobApplicationRepository.save(builder
                .withId(2)
                .forJobOpening(jobOpeningRepository.findByJobReference(new JobReference("IBM-1")))
                .ofCandidate(candidateRepository.findCandidateByEmailAddress(EmailAddress.valueOf("candidate2@jobs4u.com")))
                .withFiles(filePath)
                .withState(new ApplicationState(ApplicationStateIndicator.PENDING, null))
                .withAddonAnswers(String.format("# Enter the number of years of experience (integer)%n" +
                        "Experience-years: 2%n" +
                        "# Select one degree (None; Bachelor; Master; PhD)%n" +
                        "Academic-degree: None%n" +
                        "# Select one or more programming languages you are proficient in (java; javascript; python)%n" +
                        "Programming-languages: java%n"))
                .build());
    }
}
