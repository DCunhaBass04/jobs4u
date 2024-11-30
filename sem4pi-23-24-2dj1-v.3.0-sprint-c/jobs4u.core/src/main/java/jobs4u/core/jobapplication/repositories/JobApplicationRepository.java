package jobs4u.core.jobapplication.repositories;

import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.jobapplication.domain.ApplicationStateIndicator;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobopening.domain.JobOpening;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobApplicationRepository extends CrudRepository<JobApplication, Integer> {

    List<JobApplication> findJobApplicationsByJobOpening(JobOpening jobOpening);

    JobApplication findJobApplicationById(Integer id);
    @Query("select jobapplication from JobApplication jobapplication where jobapplication.candidate = :candidate")
    List<JobApplication> findJobApplicationByCandidate(@Param("candidate") Candidate candidate);
    @Query("select jobapplication from JobApplication jobapplication where jobapplication.candidate = :candidate and jobapplication.jobOpening.customer.customerManagerEmail = :customer_manager_email")
    List<JobApplication> findApplicationsByCandidateFromCustomerManager(@Param("candidate") Candidate candidate, @Param("customer_manager_email") EmailAddress customerManagerEmail);

    @Query("select jobapplication from JobApplication jobapplication where jobapplication.jobOpening.customer.customerManagerEmail = :customer_manager_email")
    List<JobApplication> findApplicationsFromCustomerManager(@Param("customer_manager_email") EmailAddress customerManagerEmail);

    @Query("select jobapplication from JobApplication jobapplication where jobapplication.applicationState.state = :application_state and jobapplication.jobOpening = :job_opening")
    List<JobApplication> findApplicationsInCertainPhase(@Param("application_state") ApplicationStateIndicator stateIndicator, @Param("job_opening")JobOpening jobOpening);

    @Query("select jobapplication from JobApplication jobapplication where jobapplication.jobRequirementsAnswers != '' and jobapplication.jobOpening = :job_opening")
    List<JobApplication> findApplicationsFromOpeningWithRequirementAnswers(@Param("job_opening") JobOpening jobOpening);

    @Query("select jobapplication from JobApplication jobapplication where jobapplication.interview.interviewAnswers.interviewAnswers != '' and jobapplication.jobOpening = :job_opening")
    List<JobApplication> findApplicationsFromOpeningWithInterviewModels(@Param("job_opening") JobOpening jobOpening);
    @Query("select count(application) from JobApplication application where application.jobOpening = :job_opening")
    Long countJobApplicationsForOpening(@Param("job_opening") JobOpening opening);
}
