package jobs4u.core.candidate.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import jobs4u.core.candidate.domain.NamePolicy;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.candidate.domain.PhoneNumber;

import jobs4u.core.candidate.repositories.CandidateRepository;
import jobs4u.infrastructure.auth.domain.Jobs4uPasswordPolicy;
import jobs4u.infrastructure.auth.services.Jobs4uUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UseCaseController
public class RegisterCandidateController {

    @Autowired
    private Jobs4uUserManagementService managementService;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private Jobs4uPasswordPolicy jobs4uPasswordPolicy;

    public void registerCandidate(String rawUsername, String rawPassword, String rawEmail, String firstName, String lastName, String phoneNumber) {
        Name name = Name.valueOf(firstName,lastName);
        managementService.registerCandidate(rawUsername, rawPassword, rawEmail, true);
        candidateRepository.save(new Candidate(EmailAddress.valueOf(rawEmail),name,new PhoneNumber(phoneNumber)));
    }

    public void passwordIsSatisfiedBy(String rawPassword) {
        jobs4uPasswordPolicy.PasswordIsSatisfiedBy(rawPassword);
    }

    public void emailIsSatisfiedBy(String rawEmail) {
        if (!rawEmail.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("\nInvalid email format. Email should be in the format: username@domain.com");
        }
    }

    public void firstNameIsSatisfiedBy(String firstName) {
        NamePolicy namePolicy = new NamePolicy(firstName);
    }

    public void lastNameIsSatisfiedBy(String lastName) {
        NamePolicy namePolicy = new NamePolicy(lastName);
    }

    public void phoneNumberIsSatisfiedBy(String rawPhoneNumber) {
        PhoneNumber phoneNumber = new PhoneNumber(rawPhoneNumber);
    }
}