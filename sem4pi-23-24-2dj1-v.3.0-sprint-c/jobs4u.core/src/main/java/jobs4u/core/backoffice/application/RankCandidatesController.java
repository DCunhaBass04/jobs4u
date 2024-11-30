package jobs4u.core.backoffice.application;
import eapli.framework.application.UseCaseController;
import jakarta.transaction.Transactional;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.JobReference;
import jobs4u.core.jobopening.domain.Rank;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@UseCaseController
public class RankCandidatesController {
    @Autowired
    private Jobs4uUserAuthService authService;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<JobApplication> getApplicationList(JobOpening jobOpening){
        return jobApplicationRepository.findJobApplicationsByJobOpening(jobOpening);
    }

    public void insertedNumberisAValidOption(int size, int position) {
        if (position >= size)
            throw new IllegalArgumentException("\nOption unavailable.");
        if (position < 0)
            throw new IllegalArgumentException("\nThere are no positions lower than zero. Select a number greater than or equal to 0.");
    }

    public void candidateIsNotRanked(List<Integer> positions, int position) {
        if (!positions.isEmpty()){
            for (int i = 0; i < positions.size(); i++) {
                if (positions.get(i) == position){
                    throw new IllegalArgumentException("\nThis position is already occupied.");
                }
            }
        }
    }
    @Transactional
    public void rankApplication(JobOpening jobOpening, JobApplication jobApplication, int position) {
        Rank rank = new Rank(position,jobApplication.getId());
        List<Rank> rankList = jobOpening.getRankList();
        rankList.add(rank);
        jobOpening.setRankList(rankList);
        jobOpeningRepository.save(jobOpening);
    }

    public List<Candidate> findCandidatesByJobOpening(JobOpening jobOpening) {
        List<Candidate> candidates = new ArrayList<>();
        List<JobApplication> applications = jobApplicationRepository.findJobApplicationsByJobOpening(jobOpening);

        for (JobApplication application : applications) candidates.add(application.getCandidate());
        return  candidates;
    }

    public List<JobOpening> findJobOpeningFromCustomerManager(){return jobOpeningRepository.findJobOpeningsOfCustomerManager(authService.authenticatedUser().getEmail());}

    public List<JobOpening> findJobOpeningFromCustomerManagerByCustomer(Customer customer){return jobOpeningRepository.findJobOpeningsOfCustomerManagerFromCustomer(authService.authenticatedUser().getEmail(), customer);}

    public List<Customer> findCustomersFromCustomerManager(){return customerRepository.findCustomersByCustomerManagerEmail(authService.authenticatedUser().getEmail());}

}
