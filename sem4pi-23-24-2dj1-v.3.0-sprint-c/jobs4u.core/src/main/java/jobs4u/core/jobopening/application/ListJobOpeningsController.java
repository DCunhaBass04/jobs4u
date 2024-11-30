package jobs4u.core.jobopening.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@UseCaseController
public class ListJobOpeningsController {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private Jobs4uUserAuthService jobs4uUserAuthService;
    @Autowired
    private CustomerRepository customerRepository;

    public List<JobOpening> findAll() {
        return jobOpeningRepository.findAll();
    }

    public List<JobOpening> findAllCustomerManagerJobOpenings() {
        return jobOpeningRepository.findJobOpeningsOfCustomerManager(jobs4uUserAuthService.authenticatedUser().getEmail());
    }
    public List<JobOpening> findJobOpeningFromCustomerManagerByCustomer(Customer customer){return jobOpeningRepository.findJobOpeningsOfCustomerManagerFromCustomer(jobs4uUserAuthService.authenticatedUser().getEmail(), customer);}
    public List<Customer> findCustomersFromCustomerManager(){return customerRepository.findCustomersByCustomerManagerEmail(jobs4uUserAuthService.authenticatedUser().getEmail());}
}





