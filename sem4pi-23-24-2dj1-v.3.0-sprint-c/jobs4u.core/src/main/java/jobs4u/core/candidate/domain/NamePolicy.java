package jobs4u.core.candidate.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class NamePolicy implements ValueObject{
    private String name;

    protected NamePolicy() {
    }

    public NamePolicy(String namePolicy) {
        if (!namePolicy.matches("[A-Z][a-zA-Z]*")) {
            throw new IllegalArgumentException("\nName should start with a capital letter and contain only letters.");
        }
        this.name = namePolicy;
    }
    
    public String toString() {
        return this.name;
    }
}
