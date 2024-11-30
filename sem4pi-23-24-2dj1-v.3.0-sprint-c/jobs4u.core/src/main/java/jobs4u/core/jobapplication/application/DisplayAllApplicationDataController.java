package jobs4u.core.jobapplication.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.candidate.repositories.CandidateRepository;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.ContractType;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.JobOpeningMode;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UseCaseController
public class DisplayAllApplicationDataController {
    @Autowired
    private Jobs4uUserAuthService authService;
    @Autowired
    private JobOpeningRepository jobOpeningRepository;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    public List<Customer> findCustomersWithCurrentManager(){return customerRepository.findCustomersByCustomerManagerEmail(authService.authenticatedUser().getEmail());}
    public List<JobOpening> findJobOpeningsByCustomerManager(){return jobOpeningRepository.findJobOpeningsOfCustomerManager(authService.authenticatedUser().getEmail());}
    public List<JobOpening> findJobOpeningsByCustomerManagerFromCustomer(Customer customer){return jobOpeningRepository.findJobOpeningsOfCustomerManagerFromCustomer(authService.authenticatedUser().getEmail(), customer);}
    public List<JobOpening> findJobOpeningsByCustomerManagerWithContractType(ContractType contractType){return jobOpeningRepository.findJobOpeningsOfCustomerManagerWithContractType(authService.authenticatedUser().getEmail(), contractType);}
    public List<JobOpening> findJobOpeningsByCustomerManagerWithMode(JobOpeningMode mode){return jobOpeningRepository.findJobOpeningsOfCustomerManagerWithMode(authService.authenticatedUser().getEmail(), mode);}
    public List<JobApplication> findJobApplicationsByJobOpening(JobOpening jobOpening){return jobApplicationRepository.findJobApplicationsByJobOpening(jobOpening);}
    public List<Candidate> findCandidates(){return candidateRepository.findAll();}
    public List<JobApplication> findAllApplications(){return jobApplicationRepository.findApplicationsFromCustomerManager(authService.authenticatedUser().getEmail());}
    public List<JobApplication> findJobApplicationsByCandidate(Candidate candidate){return jobApplicationRepository.findApplicationsByCandidateFromCustomerManager(candidate, authService.authenticatedUser().getEmail());}
}
