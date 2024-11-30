package jobs4u.core.jobopening.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.interviewmodel.domain.InterviewModel;
import jobs4u.core.jobopening.services.JobReferenceGenerator;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public class JobOpeningBuilder implements DomainFactory<JobOpening> {

    private JobOpening theJobOpening;
    private ContractType contractType;
    private JobOpeningFunction jobOpeningFunction;
    private JobOpeningMode jobOpeningMode;
    private JobOpeningAddress jobOpeningAddress;
    private Customer customer;
    private Vacancies vacancies;
    private LocalDateTime activeSince;
    private Long jobReferenceNumber;


    public JobOpeningBuilder withContractType(ContractType contractType) {
        this.contractType = contractType;
        return this;
    }
    public JobOpeningBuilder withJobReferenceNumber(Long jobReferenceNumber){
        this.jobReferenceNumber = jobReferenceNumber;
        return this;
    }
    public JobOpeningBuilder jobOpeningFunction(JobOpeningFunction jobOpeningFunction) {
        this.jobOpeningFunction = jobOpeningFunction;
        return this;
    }

    public JobOpeningBuilder jobOpeningMode(JobOpeningMode jobOpeningMode) {
        this.jobOpeningMode = jobOpeningMode;
        return this;
    }

    public JobOpeningBuilder jobOpeningAddress(JobOpeningAddress jobOpeningAddress) {
        this.jobOpeningAddress = jobOpeningAddress;
        return this;
    }

    public JobOpeningBuilder ofCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }
    public JobOpeningBuilder vacancies(Vacancies vacancies) {
        this.vacancies = vacancies;
        return this;
    }
    public JobOpeningBuilder activeSince(LocalDateTime activeSince) {
        this.activeSince = activeSince;
        return this;
    }
    @Override
    public JobOpening build() {
        JobOpening returnJobOpening = buildOrThrow();
        theJobOpening = null;
        return returnJobOpening;
    }
    private JobOpening buildOrThrow() {
        if (theJobOpening != null) {
            return theJobOpening;
        }
        if (contractType != null && jobOpeningFunction != null && jobOpeningMode != null && jobOpeningAddress != null && vacancies != null && customer != null && activeSince != null && jobReferenceNumber != null) {theJobOpening = new JobOpening(contractType, jobOpeningFunction, jobOpeningMode, jobOpeningAddress, customer, vacancies, activeSince, jobReferenceNumber);
            return theJobOpening;
        }
        throw new IllegalStateException();
    }

    public JobOpeningBuilder interviewModel(InterviewModel interviewModel) {
        buildOrThrow();
        theJobOpening.setInterviewModel(interviewModel);
        return this;
    }

    public JobOpeningBuilder requirements(JobRequirements jobRequirements) {
        buildOrThrow();
        theJobOpening.setJobRequirements(jobRequirements);
        return this;
    }

    public JobOpeningBuilder recruitmentProcess(RecruitmentProcess recruitmentProcess) {
        buildOrThrow();
        theJobOpening.setRecruitmentProcess(recruitmentProcess);
        return this;
    }

}
