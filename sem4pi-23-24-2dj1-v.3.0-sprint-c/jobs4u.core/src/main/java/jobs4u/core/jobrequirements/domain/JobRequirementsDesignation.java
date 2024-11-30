package jobs4u.core.jobrequirements.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobRequirementsDesignation implements ValueObject, Comparable<JobRequirementsDesignation> {

    private String designation;



    /**
     * Instantiates a new JobReuirements Desgination
     * <p>

     * <p>
     * Examples of Job Requirements
     * - "REQA"
     * - "REQB"
     * - "REQC"
     *
     * @param designation simbolized the name of the Job Requirements
     * @throws IllegalArgumentException if the String is Null or empty
     */

    public JobRequirementsDesignation(String designation) {
        if (designation.isBlank() || designation.isEmpty())
            throw new IllegalArgumentException();
        this.designation = designation;
    }

    @Override
    public int compareTo(JobRequirementsDesignation o) {
        return o.designation.compareTo(designation);
    }

    public String toString(){return designation;}

}
