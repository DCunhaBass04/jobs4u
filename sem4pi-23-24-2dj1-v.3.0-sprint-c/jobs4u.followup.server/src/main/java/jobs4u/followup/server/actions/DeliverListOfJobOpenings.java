package jobs4u.followup.server.actions;

import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.dto.JobOpeningSmallDTO;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeliverListOfJobOpenings {
    @Autowired
    private JobOpeningRepository openingRepository;
    @Autowired
    private JobApplicationRepository applicationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public List<JobOpeningSmallDTO> createListOfDTO(String customerEmail){
        List<JobOpeningSmallDTO> list = new ArrayList<>();
        Customer customer = customerRepository.findCustomerByCustomerEmail(EmailAddress.valueOf(customerEmail));
        List<JobOpening> jobOpenings = openingRepository.findJobOpeningsOfCustomer(customer);
        for(JobOpening opening : jobOpenings) list.add(opening.toSmallDto(applicationRepository.countJobApplicationsForOpening(opening)));
        return list;
    }
}
