package jobs4u.core.interviewmodel.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@EqualsAndHashCode
@Embeddable
public class InterviewModelDesignation implements ValueObject, Comparable<InterviewModelDesignation> {

    private String designation;

    protected InterviewModelDesignation() {
    }


    /**
     * Instantiates a new InterviewModel Desgination
     * <p>

     * <p>
     * Examples of Interview Model Names:
     * - "IMA"
     * - "IMD"
     * - "Something can go here , it is a free String right now"
     *
     * @param designation simbolized the name of the Interview Model
     * @throws IllegalArgumentException if the String is Null or empty
     */


    public InterviewModelDesignation(String designation) {
        if (designation.isEmpty() || designation.isBlank())
            throw new IllegalArgumentException("Invalid interview model");
        this.designation = designation;
    }

    @Override
    public int compareTo(InterviewModelDesignation otherInterview) {
        return designation.compareTo(otherInterview.getDesignation());
    }

    @Override
    public String toString() {return designation;}
}