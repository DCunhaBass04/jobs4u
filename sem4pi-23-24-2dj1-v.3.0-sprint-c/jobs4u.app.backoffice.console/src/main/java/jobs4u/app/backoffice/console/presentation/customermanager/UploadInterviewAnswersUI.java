package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.backoffice.application.UploadInterviewAnswersController;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobopening.application.ListJobOpeningsController;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.JobReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
@Transactional(readOnly = true)
public class UploadInterviewAnswersUI extends AbstractUI {

    @Autowired
    private UploadInterviewAnswersController controller;

    @Autowired
    private ListJobOpeningsUI listJobOpeningsUI;

    @Autowired
    private ListJobOpeningsController listJobOpeningsController;

    @Override
    protected boolean doShow(){
        listJobOpeningsUI.doShow();
        JobOpening jobOpening = selectJobOpening();
        JobApplication jobApplication = selectJobApplication(jobOpening);
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
        JobApplication jobApplication = null;
        List<JobApplication> jobApplications = controller.getApplicationList(jobOpening);
        showApplications(jobApplications);
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Please type the job reference of the job opening you want to rank the candidates of:%n");
        int applicationId;
        boolean verificationSuccessful = false;
        do{
            try{
                applicationId = scanner.nextInt();
                idExists(applicationId, jobApplications);
                verificationSuccessful = true;
                jobApplication = findApplication(applicationId, jobApplications);
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("\nPlease try again.\n");
            }
        } while (!verificationSuccessful);
        return jobApplication;
    }

    private void idExists(int applicationId, List<JobApplication> jobApplications) {
        boolean exists = false;
        for (int i = 0; i < jobApplications.size(); i++) {
            if (jobApplications.get(i).getId() == applicationId){
                exists = true;
                break;
            }
        }
        if (!exists){
            throw new IllegalArgumentException("\nThe selected Job Opening does not exist, try again.");
        }
    }

    private JobApplication findApplication(int applicationId, List<JobApplication> jobApplications) {
        for (int i = 0; i < jobApplications.size(); i++) {
            if (jobApplications.get(i).getId() == applicationId){
                return jobApplications.get(i);
            }
        }
        return null;
    }

    private void showApplications(List<JobApplication> jobApplications) {
        for (int i = 0; i < jobApplications.size(); i++) {
            jobApplications.get(i).toString();
        }
    }

    private JobOpening selectJobOpening() {
        String jobOpeningReference;
        JobOpening jobOpening = null;
        Scanner scanner = new Scanner(System.in);
        List<JobOpening> jobOpeningList = listJobOpeningsController.findAllCustomerManagerJobOpenings();
        System.out.printf("Please type the job reference of the job opening you want to rank the candidates of:%n");
        boolean verificationSuccessful = false;
        do{
            try{
                jobOpeningReference = scanner.next();
                controller.jobOpeningExists(jobOpeningReference);
                controller.jobOpeningIsOfTheCustomerManager(jobOpeningList, jobOpeningReference);
                verificationSuccessful = true;
                jobOpening = controller.findJobOpening(jobOpeningReference);
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("\nPlease try again.\n");
            }
        } while (!verificationSuccessful);
        return jobOpening;
    }

    @Override
    public String headline() {
        return "Rank candidates for a job application";
    }

}

