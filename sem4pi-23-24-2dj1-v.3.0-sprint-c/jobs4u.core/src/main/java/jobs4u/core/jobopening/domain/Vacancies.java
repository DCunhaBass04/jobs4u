package jobs4u.core.jobopening.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter(AccessLevel.PROTECTED)
public class Vacancies implements ValueObject {

    private Integer numberOfVacancies;

    public static Vacancies valueOf(Integer value) {
        return new Vacancies(value);
    }

    protected Vacancies() {
    }

    /**
     * Instanciates Number of Vacancies.
     * The Number of Vacancies can be a positive Number or 0
     * @param numberOfVacancies the number of vacancies
     * @throws IllegalArgumentException if the number of vacancies is negative
     */

    public Vacancies(Integer numberOfVacancies) {
        if (numberOfVacancies < 0)
            throw new IllegalArgumentException("Number of Vacancies can not be a negative number");
        this.numberOfVacancies = numberOfVacancies;
    }
    public String toString(){return numberOfVacancies.toString();}
}

