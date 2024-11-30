package jobs4u.infrastructure.bootstrappers;

import jobs4u.core.interviewmodel.domain.InterviewModel;
import jobs4u.core.interviewmodel.domain.InterviewModelDesignation;
import jobs4u.core.interviewmodel.repositories.InterviewModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterviewModelBootstrapper {

    @Autowired
    private InterviewModelRepository interviewModelRepository;

    public void bootstrap() {
        interviewModelRepository.save(new InterviewModel(new InterviewModelDesignation("IM1"), "interview.IM1.IM1Evaluator", "plugins/interviews/IM1_jar/TestGrammar.jar"));
        //interviewModelRepository.save(new InterviewModel(new InterviewModelDesignation("IMB"), "jarB.jar"));
        //interviewModelRepository.save(new InterviewModel(new InterviewModelDesignation("IMC"), "jarC.jar"));
        //interviewModelRepository.save(new InterviewModel(new InterviewModelDesignation("IMD"), "jarD.jar"));
    }

}
