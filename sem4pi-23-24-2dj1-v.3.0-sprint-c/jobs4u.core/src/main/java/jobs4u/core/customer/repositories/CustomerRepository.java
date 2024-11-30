package jobs4u.core.customer.repositories;

import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.domain.CustomerCode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, CustomerCode> {

    //provalmente é preciso mais que um metodo findAll () para cada repositorio

    @Override
    List<Customer> findAll();

    Customer findCustomerByCustomerEmail(EmailAddress email);
    Customer findCustomerByCustomerCode(CustomerCode customerCode);
    List<Customer> findCustomersByCustomerManagerEmail(EmailAddress customerManagerEmail);
}
