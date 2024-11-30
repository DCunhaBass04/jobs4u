package jobs4u.infrastructure.bootstrappers;

import jobs4u.core.jobrequirements.domain.JobRequirements;
import jobs4u.core.jobrequirements.domain.JobRequirementsDesignation;
import jobs4u.core.jobrequirements.repositories.JobRequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobRequirementsBootstrapper {

    @Autowired
    private JobRequirementsRepository jobRequirementsRepository;

    public void bootstrap() {
        jobRequirementsRepository.save(new JobRequirements(new JobRequirementsDesignation("REQ1"), "requirements.Req1.Req1Evaluator", "plugins/requirements/Req1_jar/TestGrammar.jar"));
    }
}
