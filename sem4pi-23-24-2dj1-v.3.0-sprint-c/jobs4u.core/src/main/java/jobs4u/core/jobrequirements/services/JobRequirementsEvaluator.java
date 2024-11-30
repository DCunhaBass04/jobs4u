package jobs4u.core.jobrequirements.services;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface JobRequirementsEvaluator {

    void generateTemplate(String destinyPath);

    boolean validateAnswers(File answersFile);

}

