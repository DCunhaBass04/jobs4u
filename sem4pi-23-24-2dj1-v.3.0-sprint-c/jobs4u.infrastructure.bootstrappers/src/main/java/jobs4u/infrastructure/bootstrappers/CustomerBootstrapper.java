package jobs4u.infrastructure.bootstrappers;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.domain.CustomerCode;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.DomainEvents;
import org.springframework.stereotype.Component;

@Component
public class CustomerBootstrapper {
    @Autowired
    private Jobs4uUserManagementService managementService;
    @Autowired
    private CustomerRepository customerRepository;

    public void bootstrap() {
        managementService.registerCustomer("customer", "Password1", "customer@jobs4u.com", true);
        customerRepository.save(new Customer(new CustomerCode("IBM"), Name.valueOf("Carlos", "Ferreira"), EmailAddress.valueOf("customer@jobs4u.com"), EmailAddress.valueOf("customermanager@jobs4u.com")));
        // customerRepository.save(new Customer(new CustomerCode("IBM1"), Name.valueOf("Tiago", "Pereira"), EmailAddress.valueOf("customermanager@jobs4u.com")));
        // customerRepository.save(new Customer(new CustomerCode("IBM1"), Name.valueOf("Paulo", "Sousa"), EmailAddress.valueOf("customermanager@jobs4u.com")));
    }
}
