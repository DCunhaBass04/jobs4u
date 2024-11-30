package jobs4u.core.interviewmodel.application;

import jobs4u.core.interviewmodel.domain.InterviewModel;
import jobs4u.core.interviewmodel.repositories.InterviewModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListInterviewModelsController {

    @Autowired
    private InterviewModelRepository interviewModelRepository;

    public List<InterviewModel> findAll() {
        return interviewModelRepository.findAll();
    }

}
