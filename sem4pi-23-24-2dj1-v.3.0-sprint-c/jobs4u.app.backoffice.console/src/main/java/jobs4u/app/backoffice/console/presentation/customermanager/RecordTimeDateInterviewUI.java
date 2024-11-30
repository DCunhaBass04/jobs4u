package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.domain.RecordTimeDateInterviewController;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jobs4u.core.jobopening.domain.JobOpening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecordTimeDateInterviewUI extends AbstractUI {

    @Autowired
    private RecordTimeDateInterviewController controller;

    @Override
    protected boolean doShow() {
        System.out.println("JobOpenings:\n");
        List<JobOpening> jobOpenings = controller.getJobOpeningsWithAllPhases();
        for (int i = 0; i < jobOpenings.size(); i++) {
            System.out.println((i + 1) + ":\n" + jobOpenings.get(i) + "\n");
        }
        System.out.println("\nChoose a Job Opening (0 to exit):");
        int chosenJobOpening = Console.readOption(1, jobOpenings.size(), 0);
        if(chosenJobOpening == 0) return false;

        JobOpening jobOpening = jobOpenings.get(chosenJobOpening-1);
        LocalDate startDate = controller.getStartDate(jobOpening);
        LocalDate closedDate = controller.getClosedDate(jobOpening);

        JobApplication jobApplication = chooseApplication(jobOpening);
        if (jobApplication == null) return false;
        while(true) {
            try {
                int year = Console.readInteger("\nInsert year : ");
                int month = Console.readInteger("\nInsert month : ");
                int day = Console.readInteger("\nInsert day : ");
                int hour = Console.readInteger("\nInsert hour : ");
                int minutes = Console.readInteger("\nInsert minutes : ");

                controller.recordInterviewDateTime(year, month, day, hour, minutes, jobApplication, startDate, closedDate);
                break;
            } catch(DateTimeException e) {
                System.out.println("\n" + e);
            }
        }

        System.out.println("success");
        return false;
    }
    private JobApplication chooseApplication(JobOpening opening){
        List<JobApplication> applications = controller.getJobApplicationsAccepted(opening);
        if(applications.isEmpty()) {
            System.out.println("There are no registered applications for this job opening.");
            return null;
        }
        System.out.println("Pick an Application (0 to exit):");
        for(int i = 0; i < applications.size(); i++)
            System.out.printf(" %d - Application no. %d%n", (i+1), applications.get(i).identity());
        System.out.println();
        int option = Console.readOption(1, applications.size(), 0);
        if (option == 0) {
            return null;
        }
        return applications.get(option - 1);
    }
    @Override
    public String headline() {
        return "Record Date and Time for an Interview";
    }
}