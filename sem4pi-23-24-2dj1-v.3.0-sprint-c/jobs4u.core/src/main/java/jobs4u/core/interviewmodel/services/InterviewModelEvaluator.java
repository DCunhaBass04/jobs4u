package jobs4u.core.interviewmodel.services;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface InterviewModelEvaluator {

    void generateTemplate(String destinyPath);

    int validAnswers(File answersFile);

}
