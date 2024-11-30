package jobs4u.core.interviewmodel.repositories;

import jobs4u.core.interviewmodel.domain.InterviewModel;
import jobs4u.core.interviewmodel.domain.InterviewModelDesignation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InterviewModelRepository extends CrudRepository<InterviewModel, InterviewModelDesignation> {

    InterviewModel findInterviewModelByInterviewModelDesignation(InterviewModelDesignation interviewModelDesignation);

    @Override
    List<InterviewModel> findAll();

}
