package jobs4u.infrastructure.bootstrappers;

import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.domain.CustomerCode;
import jobs4u.core.customer.repositories.CustomerRepository;
import jobs4u.core.interviewmodel.domain.InterviewModelDesignation;
import jobs4u.core.interviewmodel.repositories.InterviewModelRepository;
import jobs4u.core.jobopening.domain.*;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.core.jobrequirements.domain.JobRequirementsDesignation;
import jobs4u.core.jobrequirements.repositories.JobRequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class JobOpeningBootstrapper {
    @Autowired
    private InterviewModelRepository interviewModelRepository;
    @Autowired
    private JobRequirementsRepository requirementsRepository;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void bootstrap() {
        JobOpeningBuilder builder = new JobOpeningBuilder();

        Customer customer1 = customerRepository.findCustomerByCustomerCode(new CustomerCode("IBM"));
        JobOpeningBuilder jobOpening1 = builder.withContractType(ContractType.FULL_TIME)
                .jobOpeningFunction(new JobOpeningFunction("c programmer"))
                .jobOpeningMode(JobOpeningMode.ONSITE)
                .jobOpeningAddress(new JobOpeningAddress("Rua de trás"))
                .ofCustomer(customer1)
                .withJobReferenceNumber(1L)
                .vacancies(new Vacancies(30))
                .activeSince(LocalDateTime.now())
                .requirements(requirementsRepository.findJobRequirementsByJobRequirementsDesignation(new JobRequirementsDesignation("REQ1")))
                .interviewModel(interviewModelRepository.findInterviewModelByInterviewModelDesignation(new InterviewModelDesignation("IM1")));
        JobOpening opening1 = jobOpening1.build();
        opening1.setRecruitmentProcess(getRecruitmentProcess());
                jobOpeningRepository.save(opening1);

        JobOpeningBuilder jobOpening2 = builder.withContractType(ContractType.FULL_TIME)
                .jobOpeningFunction(new JobOpeningFunction("front end programmer"))
                .jobOpeningMode(JobOpeningMode.ONSITE)
                .jobOpeningAddress(new JobOpeningAddress("Rua de trás"))
                .ofCustomer(customer1)
                .withJobReferenceNumber(2L)
                .vacancies(new Vacancies(30))
                .activeSince(LocalDateTime.now());
        jobOpeningRepository.save(jobOpening2.build());


        JobOpeningBuilder jobOpening3 = builder.withContractType(ContractType.PART_TIME)
                .jobOpeningFunction(new JobOpeningFunction("java programmer"))
                .jobOpeningMode(JobOpeningMode.HYBRID)
                .jobOpeningAddress(new JobOpeningAddress("Rua de trás"))
                .ofCustomer(customer1)
                .withJobReferenceNumber(3L)
                .vacancies(new Vacancies(30))
                .activeSince(LocalDateTime.now());
        jobOpeningRepository.save(jobOpening3.build());
        customerRepository.save(customer1);
    }

    private static RecruitmentProcess getRecruitmentProcess() {
        return new RecruitmentProcess(List.of(
                new RecruitmentPhase(RecruitmentPhaseDesignation.APPLICATION, LocalDate.of(2024,4,4), LocalDate.of(2024,4,5)),
                new RecruitmentPhase(RecruitmentPhaseDesignation.SCREENING, LocalDate.of(2024,4,6), LocalDate.of(2024,4,7)),
                new RecruitmentPhase(RecruitmentPhaseDesignation.INTERVIEWS, LocalDate.of(2024,4,8), LocalDate.of(2024,4,9)),
                new RecruitmentPhase(RecruitmentPhaseDesignation.ANALYSIS, LocalDate.of(2024,4,10), LocalDate.of(2024,4,11)),
                new RecruitmentPhase(RecruitmentPhaseDesignation.RESULT, LocalDate.of(2024,4,12), LocalDate.of(2024,4,13))));
    }

}
