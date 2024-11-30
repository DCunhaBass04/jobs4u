package jobs4u.core.jobapplication.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.validation.constraints.NotBlank;

@Getter
@Embeddable
public class ApplicationState implements ValueObject {

    @Enumerated(EnumType.STRING)
    private ApplicationStateIndicator state;

    private String rejectionJustification;

    protected ApplicationState(){}
    public ApplicationState(ApplicationStateIndicator state, String rejectionJustification) {
        if ((state == ApplicationStateIndicator.PENDING || state == ApplicationStateIndicator.ACCEPTED) && rejectionJustification != null)
            throw new IllegalArgumentException("Accepted/Pending state must not have a justification");
        else if (state == ApplicationStateIndicator.REJECTED && rejectionJustification == null)
            throw new IllegalArgumentException("Rejection state must contain a justification");
        this.state = state;
        this.rejectionJustification = rejectionJustification;
    }
    public String toString(){
        if (state == ApplicationStateIndicator.REJECTED)
            return String.format("%s. Justification = %s.", state, rejectionJustification);
        else return state.toString();
    }
}
