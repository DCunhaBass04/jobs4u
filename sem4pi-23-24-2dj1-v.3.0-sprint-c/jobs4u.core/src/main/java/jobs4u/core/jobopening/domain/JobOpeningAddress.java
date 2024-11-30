package jobs4u.core.jobopening.domain;

import jakarta.persistence.Embeddable;
import jobs4u.core.customer.domain.CustomerCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
public class JobOpeningAddress {

    private String jobOpeningAddress;

    protected JobOpeningAddress() {

    }

    public static JobOpeningAddress valueOf(String value) {
        return new JobOpeningAddress(value);
    }

    public JobOpeningAddress(String jobOpeningAddress) {
        if (Strings.isBlank(jobOpeningAddress)) {
            throw new IllegalArgumentException("Job opening address can't be blank");
        }
        this.jobOpeningAddress = jobOpeningAddress;
    }
    public String toString(){return jobOpeningAddress;}
}
