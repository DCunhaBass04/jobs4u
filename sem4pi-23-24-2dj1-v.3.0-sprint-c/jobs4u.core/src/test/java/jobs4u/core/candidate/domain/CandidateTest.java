package jobs4u.core.candidate.domain;

import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.Name;
import jobs4u.core.backoffice.application.DisableCandidateController;
import jobs4u.infrastructure.auth.services.Jobs4uUserManagementService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import eapli.framework.general.domain.model.EmailAddress;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.infrastructure.auth.domain.Jobs4uPasswordPolicy;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import jobs4u.infrastructure.auth.domain.Password;
import jobs4u.infrastructure.auth.domain.Jobs4uUserRoles;
import jobs4u.infrastructure.auth.repositories.Jobs4uUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

class CandidateTest {

    @Test
    void sameAs_shouldReturnTrueForEqualCandidates() {
        String rawEmail = "test@example.com", firstName = "John", lastName = "Doe";
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Name name = Name.valueOf(firstName,lastName);
        Candidate candidate1 = new Candidate(
                email,name,
                new PhoneNumber("987654321")
        );
        Candidate candidate2 = new Candidate(
                email, name,
                new PhoneNumber("987654321")
        );

        boolean result = candidate1.sameAs(candidate2);

        assertTrue(result);
    }

    @Test
    void sameAs_shouldReturnFalseForDifferentCandidates() {
        String rawEmail1 = "test1@example.com", rawEmail2 = "test2@example.com", firstName = "John", lastName = "Doe";
        EmailAddress email1 = EmailAddress.valueOf(rawEmail1);
        EmailAddress email2 = EmailAddress.valueOf(rawEmail2);
        Name name = Name.valueOf(firstName,lastName);
        Candidate candidate1 = new Candidate(
                email1, name,
                new PhoneNumber("987654321")
        );
        Candidate candidate2 = new Candidate(
                email2,name,
                new PhoneNumber("987654321")
        );

        boolean result = candidate1.sameAs(candidate2);

        assertFalse(result);
    }

    @Test
    void identity_shouldReturnEmailAddress() {
        String rawEmail = "test@example.com", firstName = "John", lastName = "Doe";
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Name name = Name.valueOf(firstName,lastName);
        Candidate candidate = new Candidate(
                email, name,
                new PhoneNumber("987654321")
        );

        EmailAddress identity = candidate.identity();

        assertEquals(EmailAddress.valueOf("test@example.com"), identity);
    }

    @Test
    void testToString_shouldReturnStringRepresentation() {
        String rawEmail = "test@example.com", firstName = "John", lastName = "Doe";
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Name name = Name.valueOf(firstName,lastName);
        Candidate candidate = new Candidate(
                email, name,
                new PhoneNumber("987654321")
        );

        String result = candidate.toString();

        assertEquals(String.format("Email Address = test@example.com%n\t Name = John Doe%n\t Phone Number = 987654321%n"), result);
    }

    @Test
    void getEmailAddress_shouldReturnEmailAddress() {
        String rawEmail = "test@example.com", firstName = "John", lastName = "Doe";
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Name name = Name.valueOf(firstName,lastName);
        Candidate candidate = new Candidate(
                email, name,
                new PhoneNumber("987654321")
        );

        EmailAddress emailAddress = candidate.getEmailAddress();

        assertEquals(EmailAddress.valueOf("test@example.com"), emailAddress);
    }

    @Test
    void getName_shouldReturnName() {
        String rawEmail = "test@example.com", firstName = "John", lastName = "Doe";
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Name name = Name.valueOf(firstName,lastName);
        Candidate candidate = new Candidate(
                email, name,
                new PhoneNumber("987654321")
        );

        Name nameTest = candidate.getName();

        assertEquals(Name.valueOf("John", "Doe"), nameTest);
    }

    @Test
    void getPhoneNumber_shouldReturnPhoneNumber() {
        String rawEmail = "test@example.com", firstName = "John", lastName = "Doe";
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Name name = Name.valueOf(firstName,lastName);
        Candidate candidate = new Candidate(
                email, name,
                new PhoneNumber("987654321")
        );

        PhoneNumber phoneNumber = candidate.getPhoneNumber();

        assertEquals(new PhoneNumber("987654321"), phoneNumber);
    }
}