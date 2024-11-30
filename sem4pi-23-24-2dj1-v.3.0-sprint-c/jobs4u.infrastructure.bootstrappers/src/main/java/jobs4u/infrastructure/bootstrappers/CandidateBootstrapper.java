package jobs4u.infrastructure.bootstrappers;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.candidate.domain.PhoneNumber;
import jobs4u.core.candidate.repositories.CandidateRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidateBootstrapper {
    @Autowired
    private Jobs4uUserManagementService managementService;
    @Autowired
    private CandidateRepository candidateRepository;

    public void bootstrap() {
        managementService.registerCandidate("candidate1", "Password1", "candidate1@jobs4u.com", true);
        candidateRepository.save(new Candidate(EmailAddress.valueOf("candidate1@jobs4u.com"), Name.valueOf("João", "Nunes"), new PhoneNumber("910111222")));
        managementService.registerCandidate("candidate2", "Password1", "candidate2@jobs4u.com", true);
        candidateRepository.save(new Candidate(EmailAddress.valueOf("candidate2@jobs4u.com"), Name.valueOf("Pedro", "Teixeira"), new PhoneNumber("910111223")));
        managementService.registerCandidate("candidate3", "Password1", "candidate3@jobs4u.com", true);
        candidateRepository.save(new Candidate(EmailAddress.valueOf("candidate3@jobs4u.com"), Name.valueOf("Luís", "Ferreira"), new PhoneNumber("910111224")));
    }
}
