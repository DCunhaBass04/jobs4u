package jobs4u.core.candidate.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Embeddable
public class PhoneNumber implements ValueObject {


    private String phoneNumber;

    protected PhoneNumber() {

    }

    /**
     * Instantiates a new Phone number.
     *
     * Assuming it is a valid phone number format:
     * Must start with the digit 9, followed by exactly eight digits, with no spaces or other characters.
     *
     * Example of valid phone number: 912345678
     *
     * @param phoneNumber the phone number to be validated and assigned
     * @throws IllegalArgumentException if the phone number does not match the required format
     */

    public PhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("^9[0-9]{8}$"))
            throw new IllegalArgumentException("\nInvalid phone number format. Phone number should start with digit 9 followed by 8 digits.");
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    public String toString() {
        return this.phoneNumber;
     }

}