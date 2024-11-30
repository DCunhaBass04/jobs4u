package jobs4u.core.jobopening.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
public class JobReference implements ValueObject, Comparable<JobReference> {

    private String jobReference;

    protected JobReference() {
    }

    public JobReference(String jobReference) {
        this.jobReference = jobReference;
    }

    public static JobReference valueOf(String value) {
        return new JobReference(value);
    }


    @Override
    public int compareTo(JobReference o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobReference that = (JobReference) o;
        return this.jobReference.equals(that.jobReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobReference);
    }
    public String toString(){return jobReference;}
}
