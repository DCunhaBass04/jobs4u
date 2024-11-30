package jobs4u.core.jobopening.repositories;

import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.jobopening.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobOpeningRepository extends CrudRepository<JobOpening, JobReference> {

    @Override
    List<JobOpening> findAll();

    //@Query("select count(jobopening) from JobOpening jobopening where jobopening.jobReference.jobReference like :customer_code_value%")
    //int countCustomerJobOpenings(@Param("customer_code_value")String customerCodeValue);

    JobOpening findByJobReference(JobReference jobReference);

    Long countJobOpeningByCustomer(Customer customer);

    @Query("select jobopening from JobOpening jobopening where jobopening.customer.customerManagerEmail = :customer_manager_email")
    List<JobOpening> findJobOpeningsOfCustomerManager(@Param("customer_manager_email") EmailAddress customerManagerEmail);

    @Query("select jobopening from JobOpening jobopening where jobopening.customer = :customer")
    List<JobOpening> findJobOpeningsOfCustomer(@Param("customer") Customer customer);
    @Query("select jobopening from JobOpening jobopening where jobopening.customer.customerManagerEmail = :customer_manager_email and jobopening.jobRequirements is null")
    List<JobOpening> findJobOpeningsOfCustomerManagerWOJobRequirements(@Param("customer_manager_email") EmailAddress customerManagerEmail);

    @Query("select jobopening from JobOpening jobopening where jobopening.customer.customerManagerEmail = :customer_manager_email and jobopening.customer = :customer")
    List<JobOpening> findJobOpeningsOfCustomerManagerFromCustomer(@Param("customer_manager_email") EmailAddress customerManagerEmail, @Param("customer") Customer customer);

    @Query("select jobopening from JobOpening jobopening where jobopening.customer.customerManagerEmail = :customer_manager_email and jobopening.jobOpeningMode = :mode")
    List<JobOpening> findJobOpeningsOfCustomerManagerWithMode(@Param("customer_manager_email") EmailAddress customerManagerEmail, @Param("mode") JobOpeningMode mode);

    @Query("select jobopening from JobOpening jobopening where jobopening.customer.customerManagerEmail = :customer_manager_email and jobopening.contractType = :contract_type")
    List<JobOpening> findJobOpeningsOfCustomerManagerWithContractType(@Param("customer_manager_email") EmailAddress customerManagerEmail, @Param("contract_type") ContractType contractType);

    @Query("select jobopening from JobOpening jobopening where jobopening.customer.customerManagerEmail = :customer_manager_email")
    List<JobOpening> findJobOpeningsOfCustomerManagerNoRecruitmentProcess(@Param("customer_manager_email") EmailAddress customerManagerEmail);

    @Query("select jobopening from JobOpening jobopening where jobopening.customer.customerManagerEmail = :customer_manager_email and jobopening.customer = :customer")
    List<JobOpening> findJobOpeningsOfCustomerManagerFromCustomerNoRecruitmentProcess(@Param("customer_manager_email") EmailAddress customerManagerEmail, @Param("customer") Customer customer);
    @Query("select jobopening from JobOpening jobopening where size(jobopening.recruitmentProcess.recruitmentPhases) = 5 and jobopening.customer.customerManagerEmail = :customer_manager_email")
    List<JobOpening> findJobOpeningsOfCustomerManagerWithAllPhases(@Param("customer_manager_email") EmailAddress customerManagerEmail);
   //@Query("select count(jobopening.customer) from JobOpening jobopening where jobopening.customer = :customer")

    @Query("select count(jobopening.customer) from JobOpening jobopening where jobopening.customer = :customer")
    Long countJobOpeningsByCustomer(@Param("customer") Customer customer);

    @Query("select jobopening from JobOpening jobopening where jobopening.recruitmentProcess.currentPhase.recruitmentPhaseDesignation = :phase and jobopening.customer.customerManagerEmail = :customer_manager_email")
    List<JobOpening> findJobOpeningsFromCustomerManagerInCertainPhase(@Param("phase") RecruitmentPhaseDesignation phase, @Param("customer_manager_email") EmailAddress emailAddress);

}
