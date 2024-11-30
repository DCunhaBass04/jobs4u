package jobs4u.core.jobopening.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
public class JobOpeningFunction {

    @NotBlank
    private String jobOpeningFunction;

    protected JobOpeningFunction() {

    }

    public static JobOpeningFunction valueOf(String value) {
        return new JobOpeningFunction(value);
    }

    public JobOpeningFunction(String jobOpeningFunction) {
        this.jobOpeningFunction = jobOpeningFunction;
    }
    public String toString(){return jobOpeningFunction;}
}
