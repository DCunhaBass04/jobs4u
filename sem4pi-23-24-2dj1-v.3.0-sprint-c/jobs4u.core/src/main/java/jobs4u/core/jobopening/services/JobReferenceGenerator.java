package jobs4u.core.jobopening.services;

import jobs4u.core.customer.domain.Customer;
import jobs4u.core.jobopening.application.RegisterJobOpeningController;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.JobReference;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servico de dominio Nao deve aceder a base de dados
 * Service find all jobOpenings by a query
 */

@Service
public class JobReferenceGenerator {
    public static JobReference generateJobReference(Customer customer, Long value) {
        return JobReference.valueOf(customer.getCustomerCode().value().toUpperCase() + "-" + value);
    }
}