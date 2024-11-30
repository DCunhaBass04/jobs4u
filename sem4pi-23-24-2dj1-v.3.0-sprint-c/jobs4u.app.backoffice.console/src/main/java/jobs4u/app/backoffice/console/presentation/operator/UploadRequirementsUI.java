package jobs4u.app.backoffice.console.presentation.operator;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.backoffice.application.UploadRequirementsController;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobopening.domain.JobOpening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
@Transactional(readOnly = true)
public class UploadRequirementsUI extends AbstractUI {

    @Autowired
    private UploadRequirementsController controller;

    @Override
    protected boolean doShow(){
        System.out.println("JobOpenings:\n");
        List<JobOpening> jobOpenings = controller.getJobOpenings();
        System.out.println("Please choose an option (0 to exit)");
        for (int i = 0; i < jobOpenings.size(); i++) {
            System.out.println((i + 1) + ":\n" + jobOpenings.get(i) + "\n");
        }
        JobOpening jobOpening = selectJobOpening(jobOpenings);
        if(jobOpening == null) return false;
        JobApplication jobApplication = selectJobApplication(jobOpening);
        if(jobApplication == null) return false;
        try {
            selectAndUploadFile(jobApplication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private void selectAndUploadFile(JobApplication jobApplication) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName;
        System.out.printf("Please type the name of the file you want to upload:%n");
        fileName = scanner.next();
        controller.uploadFile(fileName, jobApplication);
    }

    private JobApplication selectJobApplication(JobOpening jobOpening){
        List<JobApplication> jobApplications = controller.getApplicationList(jobOpening);
        System.out.println("Please choose an option (0 to exit)");
        showApplications(jobApplications);
        int option = Console.readOption(1,jobApplications.size(),0);
        if (option == 0) return null;
        return jobApplications.get(option-1);
    }

    private void showApplications(List<JobApplication> jobApplications) {
        for (int i = 0; i < jobApplications.size(); i++) {
            System.out.printf("%d - Application no. %d%n", i+1, jobApplications.get(i).getId());
        }
    }

    private JobOpening selectJobOpening(List<JobOpening> openings) {
        System.out.println("Please choose a Job Opening (0 to exit)");
        int option = Console.readOption(1,openings.size(),0);
        if (option == 0) return null;
        return openings.get(option-1);
    }

    @Override
    public String headline() {
        return "Upload Requirements for a Job Application";
    }

}