package jobs4u.core.jobapplication.domain;

import eapli.framework.domain.model.DomainFactory;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.jobopening.domain.JobOpening;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JobApplicationBuilder implements DomainFactory<JobApplication> {
    private JobApplication theJobApplication;
    private Integer id;
    private JobOpening jobOpening;
    private Candidate candidate;
    private List<ApplicationFile> applicationFiles;
    private ApplicationState applicationState;

    public JobApplicationBuilder forJobOpening(JobOpening jobOpening) {
        this.jobOpening = jobOpening;
        return this;
    }
    public JobApplicationBuilder withId(Integer id){
        this.id = id;
        return this;
    }
    public JobApplicationBuilder withState(ApplicationState applicationState){
        this.applicationState = applicationState;
        return this;
    }
    public JobApplicationBuilder ofCandidate(Candidate candidate) {
        this.candidate = candidate;
        return this;
    }

    public JobApplicationBuilder withFiles(String filePath) {
        List<ApplicationFile> applicationFiles = new ArrayList<>();
        List<String> fileNames = Stream.of(new File(String.format("%s/%s/%s", filePath, jobOpening.getJobReference().toString(), id)).listFiles())
                .filter(file -> !file.isDirectory()) //Only get files
                .map(File::getName) //Get names
                .toList(); //Send them to List
        for (String fileName : fileNames){
            if(fileName.contains("big-file")) applicationFiles.add(new ApplicationFile(String.format("%s/%s/%s/%s", filePath, jobOpening.getJobReference().toString(), id, fileName), ApplicationFileFunction.BIG_FILE));
            else if(fileName.contains("candidate-data")) applicationFiles.add(new ApplicationFile(String.format("%s/%s/%s/%s", filePath, jobOpening.getJobReference().toString(), id, fileName), ApplicationFileFunction.CANDIDATE_DATA));
            else if(fileName.contains("cv")) applicationFiles.add(new ApplicationFile(String.format("%s/%s/%s/%s", filePath, jobOpening.getJobReference().toString(), id, fileName), ApplicationFileFunction.CV));
            else if(fileName.contains("email")) applicationFiles.add(new ApplicationFile(String.format("%s/%s/%s/%s", filePath, jobOpening.getJobReference().toString(), id, fileName), ApplicationFileFunction.EMAIL));
            else if(fileName.contains("report")) applicationFiles.add(new ApplicationFile(String.format("%s/%s/%s/%s", filePath, jobOpening.getJobReference().toString(), id, fileName), ApplicationFileFunction.REPORT));
            else if(fileName.contains("letter")) applicationFiles.add(new ApplicationFile(String.format("%s/%s/%s/%s", filePath, jobOpening.getJobReference().toString(), id, fileName), ApplicationFileFunction.LETTER));
        }
        this.applicationFiles = applicationFiles;
        return this;
    }

    public JobApplicationBuilder withAddonInterview(Interview interview) {
        buildOrThrow();
        theJobApplication.setInterview(interview);
        return this;
    }
    public JobApplicationBuilder withAddonAnswers(String jobRequirementsAnswers) {
        buildOrThrow();
        theJobApplication.setJobRequirementsAnswers(jobRequirementsAnswers);
        return this;
    }

    @Override
    public JobApplication build() {
        JobApplication returnApplication = buildOrThrow();
        theJobApplication = null;
        return returnApplication;
    }

    private JobApplication buildOrThrow() {
        if (theJobApplication != null) {
            return theJobApplication;
        }
        if (jobOpening != null && candidate != null && applicationFiles != null && applicationState != null) {
            theJobApplication = new JobApplication(id, jobOpening, candidate, applicationFiles, applicationState);
            return theJobApplication;
        }
        throw new IllegalStateException();
    }

}
