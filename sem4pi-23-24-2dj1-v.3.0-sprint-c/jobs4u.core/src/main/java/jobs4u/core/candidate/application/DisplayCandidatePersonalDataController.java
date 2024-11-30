package jobs4u.core.candidate.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.Name;
import jobs4u.core.candidate.domain.Candidate;

import jobs4u.core.candidate.domain.NamePolicy;
import jobs4u.core.candidate.repositories.CandidateRepository;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UseCaseController
public class DisplayCandidatePersonalDataController {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateByName(String firstName, String lastName){
        Name name = Name.valueOf(firstName,lastName);
        return candidateRepository.findCandidateByName(name);
    }

    public void firstNameIsSatisfiedBy(String firstName) {
        NamePolicy namePolicy = new NamePolicy(firstName);
    }

    public void lastNameIsSatisfiedBy(String lastName) {
        NamePolicy namePolicy = new NamePolicy(lastName);
    }
}
