package jobs4u.core.customer.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "customer")
public class Customer implements AggregateRoot<CustomerCode> {

    @EmbeddedId
    private CustomerCode customerCode;

    @Embedded
    private Name name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "customer_email", unique = true))
    })
    private EmailAddress customerEmail;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "customer_manager_email"))
    })
    private EmailAddress customerManagerEmail;


    protected Customer() {

    }


    public Customer(CustomerCode customerCode, Name name, EmailAddress email, EmailAddress customerManagerEmail) {
        Preconditions.noneNull(customerCode,name,customerManagerEmail);
        this.customerCode = customerCode;
        this.name = name;
        this.customerEmail = email;
        this.customerManagerEmail = customerManagerEmail;
    }

    @Override
    public boolean sameAs(Object other) {return DomainEntities.areEqual(this, other);}



    @Override
    public CustomerCode identity() {return customerCode;}

    public String toString(){return String.format("Customer %s%n Name = %s%n Email = %s%n Customer Manager's Email = %s%n ", customerCode, name, customerEmail, customerManagerEmail);}

}