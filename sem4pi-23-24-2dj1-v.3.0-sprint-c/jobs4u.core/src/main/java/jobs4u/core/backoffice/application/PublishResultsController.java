package jobs4u.core.backoffice.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.Rank;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@UseCaseController
public class PublishResultsController {

    @Autowired
    private Jobs4uUserAuthService authService;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<JobOpening> findJobOpeningFromCustomerManager(){return jobOpeningRepository.findJobOpeningsOfCustomerManager(authService.authenticatedUser().getEmail());}

    public List<JobOpening> findJobOpeningFromCustomerManagerByCustomer(Customer customer){return jobOpeningRepository.findJobOpeningsOfCustomerManagerFromCustomer(authService.authenticatedUser().getEmail(), customer);}

    public List<Customer> findCustomersFromCustomerManager(){return customerRepository.findCustomersByCustomerManagerEmail(authService.authenticatedUser().getEmail());}

    public List<Rank> getResults(JobOpening jobOpening){
        List<Rank> results = new ArrayList<>();
        List<Rank> rankList = jobOpening.getRankList();
        int numberOfVacancies = jobOpening.getNumberOfVacancies().getNumberOfVacancies();

        Rank candidatoAtual;
        for (int i = 0; i < numberOfVacancies; i++) {
            candidatoAtual =  findPositionInRankList(i, rankList);
            results.add(candidatoAtual);
        }
        
        return results;
    }

    private Rank findPositionInRankList(int position, List<Rank> rankList) {
        for (int i = 0; i < rankList.size(); i++) {
            if(position == rankList.get(i).getPosition()){
                return rankList.get(i);
            }
        }
        return null;
    }

    public void publishResults(List<Rank> results){

    }
}
