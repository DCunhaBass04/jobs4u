package jobs4u.core.customer.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.domain.CustomerCode;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import jobs4u.infrastructure.auth.services.Jobs4uUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@UseCaseController
public class RegisterCustomerController {

    @Autowired
    private Jobs4uUserManagementService managementService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Jobs4uUserAuthService authService;

    @Transactional
    public void registerCustomer(String rawUsername, String rawPassword, String rawEmail, String firstName, String lastName, String customerCode) {
        managementService.registerCustomer(rawUsername, rawPassword, rawEmail, true);
        customerRepository.save(new Customer(new CustomerCode(customerCode), Name.valueOf(firstName, lastName), EmailAddress.valueOf(rawEmail), authService.authenticatedUser().getEmail()));
    }
}
