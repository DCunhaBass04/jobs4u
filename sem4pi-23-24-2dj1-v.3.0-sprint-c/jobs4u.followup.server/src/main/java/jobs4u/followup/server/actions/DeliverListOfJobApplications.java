package jobs4u.followup.server.actions;

import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.dto.JobApplicationSmallDTO;
import jobs4u.core.jobapplication.repositories.JobApplicationRepository;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.dto.JobOpeningSmallDTO;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeliverListOfJobApplications {
    @Autowired
    private JobOpeningRepository openingRepository;
    @Autowired
    private JobApplicationRepository applicationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public List<JobApplicationSmallDTO> createListOfDTO(Candidate candidate){
        List<JobApplicationSmallDTO> list = new ArrayList<>();
        List<JobApplication> jobApplications = applicationRepository.findJobApplicationByCandidate(candidate);
        for(JobApplication application : jobApplications) list.add(application.toSmallDto(applicationRepository.countJobApplicationsForOpening(application.getJobOpening())));
        return list;
    }
}
