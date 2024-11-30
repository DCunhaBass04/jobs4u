package jobs4u.app.backoffice.console.presentation.customermanager;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.jobopening.application.SetupJobOpeningRecuitmentPhasesController;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.domain.RecruitmentPhase;
import jobs4u.core.jobopening.domain.RecruitmentPhaseDesignation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SetupJobOpeningRecruitmentPhasesUI extends AbstractUI {

    @Autowired
    private SetupJobOpeningRecuitmentPhasesController controller;

    @Override
    protected boolean doShow() {
        JobOpening affectedJobOpening = chooseJobOpening();
        if(affectedJobOpening == null)
            return false;
        List<RecruitmentPhase> phases = new ArrayList<>();
        List<LocalDate> dates = getDates(null, "Application");
        RecruitmentPhase phase = new RecruitmentPhase(RecruitmentPhaseDesignation.APPLICATION, dates.get(0), dates.get(1));
        phases.add(phase);
        dates = getDates(dates.get(1), "Screening");
        phase = new RecruitmentPhase(RecruitmentPhaseDesignation.SCREENING, dates.get(0), dates.get(1));
        phases.add(phase);
        if(Console.readBoolean("Insert Interviews phase? (y/n)")){
            dates = getDates(dates.get(1), "Interviews");
            phase = new RecruitmentPhase(RecruitmentPhaseDesignation.INTERVIEWS, dates.get(0), dates.get(1));
            phases.add(phase);
        }
        dates = getDates(dates.get(1), "Analysis");
        phase = new RecruitmentPhase(RecruitmentPhaseDesignation.ANALYSIS, dates.get(0), dates.get(1));
        phases.add(phase);
        dates = getDates(dates.get(1), "Result");
        phase = new RecruitmentPhase(RecruitmentPhaseDesignation.RESULT, dates.get(0), dates.get(1));
        phases.add(phase);

        controller.updateJobOpening(affectedJobOpening, phases);
        return false;
    }
    private JobOpening chooseJobOpening(){
        List<JobOpening> jobOpenings = controller.findCustomerManagerJobOpeningsNoRecruitmentProcess();
        if(jobOpenings.isEmpty()) {
            System.out.println("You have no registered Job Openings without a Recruitment Process setup.");
            return null;
        }
        System.out.println("Pick a Job Opening (0 to exit):");
        for(int i = 0; i < jobOpenings.size(); i++)
            System.out.printf(" %d - %s%n", (i+1), jobOpenings.get(i).getJobReference());
        System.out.println();
        int option = Console.readOption(1, jobOpenings.size(), 0);
        if (option == 0) {
            return null;
        }
        return jobOpenings.get(option - 1);
    }
    private List<LocalDate> getDates(LocalDate previousEndDate, String phaseName){
        LocalDate startDate = null, endDate = null;
        boolean valid = false;
        while(!valid) {
            valid = true;
            startDate = readDate(String.format("Insert a start date for the %s phase (yyyy-mm-dd)", phaseName));
            if (previousEndDate != null && startDate.isBefore(previousEndDate)) {
                System.out.println("Phase cannot begin before the ending of the previous phase.");
                valid = false;
            }
            if(valid) {
                endDate = readDate(String.format("Insert an end date for the %s phase (yyyy-mm-dd)", phaseName));
                if (startDate.isAfter(endDate)) {
                    System.out.println("End date cannot be before start date");
                    valid = false;
                }
            }
        }
        return Arrays.asList(startDate, endDate);
    }
    private LocalDate readDate(String prompt){
        while(true) {
            try {
                String strDate = Console.readLine(prompt);
                return LocalDate.parse(strDate);
            } catch (DateTimeParseException e) {
                System.out.println("Text was incorrectly inserted. Try again.");
            }
        }
    }
    @Override
    public String headline() {return "Setup job opening recruitment phases";}
}
