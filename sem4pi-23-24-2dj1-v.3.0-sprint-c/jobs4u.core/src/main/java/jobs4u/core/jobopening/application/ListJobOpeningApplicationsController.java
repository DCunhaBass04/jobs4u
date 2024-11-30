package jobs4u.core.jobopening.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UseCaseController
public class ListJobOpeningApplicationsController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private Jobs4uUserAuthService authService;

    public List<JobApplication> findJobApplicationsByJobOpening(JobOpening jobOpening){return jobApplicationRepository.findJobApplicationsByJobOpening(jobOpening);}

    public List<JobOpening> findJobOpeningFromCustomerManager(){return jobOpeningRepository.findJobOpeningsOfCustomerManager(authService.authenticatedUser().getEmail());}

    public List<JobOpening> findJobOpeningFromCustomerManagerByCustomer(Customer customer){return jobOpeningRepository.findJobOpeningsOfCustomerManagerFromCustomer(authService.authenticatedUser().getEmail(), customer);}

    public List<Customer> findCustomersFromCustomerManager(){return customerRepository.findCustomersByCustomerManagerEmail(authService.authenticatedUser().getEmail());}

}
