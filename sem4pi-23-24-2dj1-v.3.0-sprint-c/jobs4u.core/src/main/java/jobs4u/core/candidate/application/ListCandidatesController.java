package jobs4u.core.candidate.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UseCaseController
public class ListCandidatesController {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> findAllCandidates() {
        return candidateRepository.findAll();
    }

}
