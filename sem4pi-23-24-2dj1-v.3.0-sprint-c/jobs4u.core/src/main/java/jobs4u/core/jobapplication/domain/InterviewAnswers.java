package jobs4u.core.jobapplication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Lob;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Embeddable
public class InterviewAnswers {
    @Lob
    @NotBlank
    @Column(columnDefinition = "LONGTEXT")
    private String interviewAnswers;
    public String toString(){return interviewAnswers;}

    public void setInterviewAnswers(String interviewAnswers) {
        this.interviewAnswers = interviewAnswers;
    }
}
