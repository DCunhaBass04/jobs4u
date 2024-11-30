package jobs4u.core.jobopening.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.domain.CustomerCode;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.core.jobopening.domain.*;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Register job opening controller.
 */
@Component
@UseCaseController
public class RegisterJobOpeningController {

    @Autowired
    private Jobs4uUserAuthService jobs4uUserAuthService;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();


    private List<JobOpening> findJobOpenings() {return jobOpeningRepository.findAll();}

    /**
     * Register job opening.
     *
     * @param rawJobOpeningFunction the raw job opening function
     * @param contractType          the contract type
     * @param jobOpeningMode        the job opening mode
     * @param rawJobOpeningAddress  the raw job opening address
     * @param rawVacancies          the raw vacancies
     * @param customer              the customer
     */

    @Transactional
    public void registerJobOpening(String rawJobOpeningFunction, ContractType contractType, JobOpeningMode jobOpeningMode,
                                   String rawJobOpeningAddress, Integer rawVacancies, Customer customer) {

                        jobOpeningBuilder.jobOpeningAddress(JobOpeningAddress.valueOf(rawJobOpeningAddress))
                        .jobOpeningFunction(JobOpeningFunction.valueOf(rawJobOpeningFunction))
                        .jobOpeningMode(jobOpeningMode)
                        .ofCustomer(customer)
                        .withJobReferenceNumber(jobOpeningRepository.countJobOpeningsByCustomer(customer)+1)
                        .vacancies(Vacancies.valueOf(rawVacancies))
                        .withContractType(contractType)
                        .activeSince(LocalDateTime.now());
                        // build chama customer e cria Job Reference
        jobOpeningRepository.save(jobOpeningBuilder.build());
    }

    public Long  countJobOpeningByCustomer(Customer customer){return jobOpeningRepository.countJobOpeningsByCustomer(customer);}

    public List<Customer> findCustomersFromCustomerManager(){return customerRepository.findCustomersByCustomerManagerEmail(jobs4uUserAuthService.authenticatedUser().getEmail());}
}
