package jobs4u.core.jobopening.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.interviewmodel.domain.InterviewModel;
import jobs4u.core.jobopening.domain.*;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@UseCaseController

public class EditJobOpeningController {

    @Autowired
    private Jobs4uUserAuthService authService;

    @Autowired
    private JobOpeningRepository jobOpeningRepository;


    /**
     * Find customer manager job openings list by users email.
     *
     * @return the list
     */
    public List<JobOpening> findCustomerManagerJobOpenings() {

        return jobOpeningRepository.findJobOpeningsOfCustomerManager(authService.authenticatedUser().getEmail());
    }


    @Transactional
    public void editJobOpeningWithNoPhase(JobOpening jobOpening, String rawJobOpeningFunction, ContractType contractType, JobOpeningMode jobOpeningMode,
                                          String rawJobOpeningAddress, Integer rawVacancies) {

        jobOpening.setJobOpeningFunction(JobOpeningFunction.valueOf(rawJobOpeningFunction));
        jobOpening.setContractType(contractType);
        jobOpening.setJobOpeningMode(jobOpeningMode);
        jobOpening.setJobOpeningAddress(JobOpeningAddress.valueOf(rawJobOpeningAddress));
        jobOpening.setNumberOfVacancies(Vacancies.valueOf(rawVacancies));
        jobOpeningRepository.save(jobOpening);
    }

    @Transactional
    public void editJobOpeningOnApplicationPhase(JobOpening jobOpening, InterviewModel newInterviewModel, JobRequirements newJobRequirements) {
        jobOpening.setInterviewModel(newInterviewModel);
        jobOpening.setJobRequirements(newJobRequirements);
        jobOpeningRepository.save(jobOpening);
    }


}

