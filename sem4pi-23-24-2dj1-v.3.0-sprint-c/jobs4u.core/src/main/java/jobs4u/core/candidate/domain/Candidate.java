package jobs4u.core.candidate.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;

import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "candidate")
public class Candidate implements AggregateRoot<EmailAddress> {

    @EmbeddedId
    @Column(name = "EMAIL")
    private EmailAddress emailAddress;

    @Embedded
    private Name name;

    @Embedded
    private PhoneNumber phoneNumber;

    protected Candidate() {

    }

    public Candidate(EmailAddress emailAddress, Name name, PhoneNumber phoneNumber) {
        Preconditions.noneNull(emailAddress,name,phoneNumber);
        this.emailAddress = emailAddress;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public EmailAddress identity() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return String.format("Email Address = %s%n\t Name = %s%n\t Phone Number = %s%n", emailAddress, name, phoneNumber);
    }


}