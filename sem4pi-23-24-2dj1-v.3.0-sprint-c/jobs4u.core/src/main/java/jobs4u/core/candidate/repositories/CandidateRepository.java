package jobs4u.core.candidate.repositories;

import eapli.framework.general.domain.model.EmailAddress;

import eapli.framework.infrastructure.authz.domain.model.Name;
import jobs4u.core.candidate.domain.Candidate;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidateRepository extends CrudRepository<Candidate, EmailAddress> {

    Candidate findCandidateByEmailAddress(EmailAddress emailAddress);

    @Override
    List<Candidate> findAll();

    Candidate findCandidateByName(Name name);

}
