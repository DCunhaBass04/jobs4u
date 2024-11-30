package jobs4u.core.customer.domain;


import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Customer code.
 */
@Embeddable
@Getter
@Accessors(fluent = true)
public class CustomerCode implements ValueObject, Comparable<CustomerCode> {

    private String value;

    protected CustomerCode() {

    }


    /**
     * Instantiates a new Customer code.
     * <p>
     * The customer code is expected to be a single string without any spaces before or after it.
     * It has a maximum 10 characters long, only capital letters,starts with letter,it can contain numbers)
     * <p>
     * Examples of valid codes:
     * - "ISEP"
     * - "DEI2324"
     * - "DEI2324"
     *
     * @param value the customer code to be validated and assigned
     * @throws IllegalArgumentException if the code does not match the required format
     */

    public CustomerCode(String value) {
        if (!validCode(value)) {
            throw new IllegalArgumentException("Customer code not in a valid format.");
        }
        this.value = value;
    }


    @Override
    public int compareTo(CustomerCode o) {
        return this.value.compareTo(o.value);
    }

    public static CustomerCode valueOf(String value) {
        return new CustomerCode(value);
    }

    private static boolean validCode(String input) {
        String regex = "^[A-Z][A-Z0-9]{0,9}$";  //  1-10 Caracteres long
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public String toString() {
        return value;
    }
}