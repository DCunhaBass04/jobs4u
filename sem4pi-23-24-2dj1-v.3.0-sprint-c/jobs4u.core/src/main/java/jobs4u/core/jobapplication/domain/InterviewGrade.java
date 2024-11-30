package jobs4u.core.jobapplication.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Embeddable
public class InterviewGrade {

    @Min(1) @Max(100)
    private int grade;

    @NotBlank
    private String justification;

    public String toString(){return String.format("%d%n%s", grade, justification);}
}
