package jobs4u.core.jobapplication.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.jobapplication.dto.JobApplicationSmallDTO;
import jobs4u.core.jobapplication.services.WordCounter;
import jobs4u.core.jobapplication.services.WordQueue;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.dto.JobOpeningSmallDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import javax.validation.constraints.NotEmpty;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class JobApplication implements AggregateRoot<Integer> {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "JOB_OPENING")
    private JobOpening jobOpening;

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_EMAIL")
    private Candidate candidate;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String jobRequirementsAnswers;

    @ElementCollection(targetClass = ApplicationFile.class, fetch = FetchType.EAGER)
    private List<ApplicationFile> applicationFiles;

    @Embedded
    private Interview interview;

    @Embedded
    private ApplicationState applicationState;

    protected JobApplication(Integer id, JobOpening jobOpening, Candidate candidate, List<ApplicationFile> applicationFiles, ApplicationState applicationState) {
        Preconditions.noneNull(id, jobOpening, candidate, applicationFiles, applicationState);

        this.id = id;
        this.jobOpening = jobOpening;
        this.candidate = candidate;
        this.applicationFiles = applicationFiles;
        this.applicationState = applicationState;
    }
    public void addInterview(Interview interview){
        Preconditions.nonNull(interview);
        this.interview = interview;
    }
    public void changeStateTo(ApplicationState applicationState){
        Preconditions.nonNull(applicationState);
        this.applicationState = applicationState;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Integer identity() {return id;}
    public String toString(){return String.format("Job Application %d%n Belongs to Job Opening %s%n From candidate %s%n State = %s%n", id, jobOpening.getJobReference(), candidate.getName(), applicationState);}
    public List<Map.Entry<String, Map.Entry<List<String>, Integer>>> createListOfWords(int numWords) throws InterruptedException {
        WordQueue words = new WordQueue();
        int numThreads = applicationFiles.size();
        List<String> fileNames = getFileNames();
        Thread[] threads = new Thread[numThreads];
        for(int i = 0; i < numThreads; i++){
            WordCounter counter = new WordCounter(words, fileNames.get(i));
            threads[i] = new Thread(counter);
            threads[i].start();
        }
        for(Thread thread : threads) thread.join();
        return words.getSortedListOfMostUsedWords(numWords);
    }
    public List<String> getFileNames(){
        List<String> fileNames = new ArrayList<>();
        for(ApplicationFile file: applicationFiles) fileNames.add(file.path());
        return fileNames;
    }
    public String details(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Job Application no. %d%n Belongs to Job Opening %s%n From candidate %s%n State = %s%n", id, jobOpening.getJobReference(), candidate.getName(), applicationState));
        for (ApplicationFile file : applicationFiles)
            builder.append(String.format(" %s", file));
        if (interview != null) builder.append(String.format(" Interview: %s%n", interview));
        if (jobRequirementsAnswers != null && !jobRequirementsAnswers.isEmpty()) builder.append(String.format(" Requirements: %s%n%n", jobRequirementsAnswers));
        return builder.toString();
    }
    public void updateStatus(String feedback){
        Scanner readString = new Scanner(feedback);
        String result = readString.nextLine();
        if(result.equalsIgnoreCase("Passed")){
            setApplicationState(new ApplicationState(ApplicationStateIndicator.ACCEPTED, null));
            return;
        }
        if(result.equalsIgnoreCase("Failed")) {
            StringBuilder justifications = new StringBuilder();
            while (readString.hasNext()) {
                justifications.append(String.format("%s%n", readString.nextLine()));
            }
            setApplicationState(new ApplicationState(ApplicationStateIndicator.REJECTED, justifications.toString()));
        }
        //Else, status isn't updated
    }
    public void updateGrade(String feedback){
        Scanner readString = new Scanner(feedback);
        String result = readString.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(result);
        int score = 0;
        if(matcher.find())
            score = Integer.parseInt(matcher.group());
        StringBuilder builder = new StringBuilder();
        while(readString.hasNext()){
            builder.append(String.format("%s%n",readString.nextLine()));
        }
        this.interview.grade(new InterviewGrade(score, builder.toString()));
        //Else, status isn't updated
    }

    public void setInterview(Interview interview){
        this.interview = interview;
    }

    public void setJobRequirementsAnswers(String jobRequirementsAnswers){this.jobRequirementsAnswers = jobRequirementsAnswers;}
    public void recordTimeDateInterview(int year, int month, int day, int hour, int minutes, LocalDate startDate, LocalDate closedDate) {
        LocalDate localDate = LocalDate.of(year, month, day);
        LocalDateTime localDateTIme = LocalDateTime.of(year, month, day, hour, minutes);
        addInterviewInTimeframe(localDateTIme, localDate, startDate, closedDate);
    }
    public void addInterviewInTimeframe(LocalDateTime localDateTime, LocalDate localDate, LocalDate starDate, LocalDate closeDate){
        jobOpening.verifyIfIsInIterviewPhaseTimeframe(localDate, starDate, closeDate);
        interview = new Interview(localDateTime);
        addInterview(interview);
    }
    public JobApplicationSmallDTO toSmallDto(long numApplicants){
        return new JobApplicationSmallDTO(id, applicationState, numApplicants);
    }
}