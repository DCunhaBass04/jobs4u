package jobs4u.core.jobapplication.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Interview {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime interviewDateTime;

    @Embedded
    private InterviewGrade interviewGrade;

    @Getter
    @Embedded
    private InterviewAnswers interviewAnswers;
    public Interview(LocalDateTime interviewDateTime){
        if(interviewDateTime == null)
            throw new IllegalStateException();
        this.interviewDateTime = interviewDateTime;
    }
    public Interview(LocalDateTime interviewDateTime, InterviewGrade interviewGrade, InterviewAnswers interviewAnswers){
        if(interviewDateTime == null || interviewGrade == null || interviewAnswers == null)
            throw new IllegalStateException();
        this.interviewDateTime = interviewDateTime;
        this.interviewGrade = interviewGrade;
        this.interviewAnswers = interviewAnswers;
    }

    public void grade(InterviewGrade interviewGrade){
        if(interviewGrade == null)
            throw new IllegalStateException();
        this.interviewGrade = interviewGrade;
    }
    public void answer(InterviewAnswers interviewAnswers){
        if(interviewAnswers == null)
            throw new IllegalStateException();
        this.interviewAnswers = interviewAnswers;
    }

    public void setInterviewAnswers(InterviewAnswers interviewAnswers){this.interviewAnswers = interviewAnswers;
    }

    public String toString(){
        return String.format("Interview was scheduled for %02d/%02d/%02d %02d:%02d%n",
                interviewDateTime.getDayOfMonth(), interviewDateTime.getMonthValue(), interviewDateTime.getYear(),
                interviewDateTime.getHour(), interviewDateTime.getMinute()) +
                String.format("  Answers: %s%n", (interviewAnswers != null) ? interviewAnswers.toString() : "Answers were not yet submitted.") +
                String.format("  Grade: %s", (interviewGrade != null) ? interviewGrade.toString() : "Interview was not graded yet.");
    }
}
