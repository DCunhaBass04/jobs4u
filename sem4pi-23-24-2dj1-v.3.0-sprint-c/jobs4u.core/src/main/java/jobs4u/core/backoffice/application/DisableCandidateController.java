package jobs4u.core.backoffice.application;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.EmailAddress;
import jakarta.transaction.Transactional;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.candidate.repositories.CandidateRepository;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import jobs4u.infrastructure.auth.repositories.Jobs4uUserRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UseCaseController
public class DisableCandidateController {

    @Autowired
    private Jobs4uUserRepository userRepository;

    @Autowired
    CandidateRepository candidateRepository;

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public void findCandidateAndToggleStatus(String email){
        Jobs4uUser user = userRepository.findJobs4uUserByEmail(EmailAddress.valueOf(email));
        toggleUserStatus(user);
    }

    @Transactional
    public void toggleUserStatus(Jobs4uUser user){
        user.toggleStatus();
        userRepository.save(user);
    }

    public void emailIsSatisfiedBy(String rawEmail) {
        if (!rawEmail.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("\nInvalid email format. Email should be in the format: username@domain.com");
        }
    }

    public void emailIsOfACandidate(String rawEmail) {
        EmailAddress emailAddress = EmailAddress.valueOf(rawEmail);
        if(candidateRepository.findCandidateByEmailAddress(emailAddress) == null){
            throw new IllegalArgumentException("\nThere is no candidate registered with this email address. The available emails are available in the candidate list.");
        }
    }
}
