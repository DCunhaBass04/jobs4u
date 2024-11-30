package jobs4u.app.backoffice.console.presentation.operator;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobrequirements.application.ExportRequirementsTemplateFileController;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

import java.util.List;

@Component
public class ExportRequirementsTemplateFileUI extends AbstractUI {

    @Autowired
    private ExportRequirementsTemplateFileController controller;


    @Override
    protected boolean doShow() {
        Scanner scanner = new Scanner(System.in);
        List<JobOpening> jobOpenings = controller.getJobOpenings();
        showList(jobOpenings);
        System.out.printf("Please select an option.%n");
        int option;
        boolean valid;
        do {
            valid = true;
            option = scanner.nextInt();
            if (option < 0 || option >= jobOpenings.size()){
                valid = false;
                System.out.printf("Invalid option. Try again.%n");
            }
        } while (!valid);
        JobOpening jobOpening = jobOpenings.get(option);
        try {
            controller.getAndExportTemplate(jobOpening);
        } catch (Exception e) {
            System.out.println("This Job Opening doesn't have Requirements set up");
        }
        return false;
    }

    private void showList(List<JobOpening> jobOpenings){
        for (int i = 0; i < jobOpenings.size(); i++) {
            System.out.printf("%d - %s%n", i,jobOpenings.get(i));
        }
    }

    @Override
    public String headline() {
        return "Export job requirements template file";
    }
}
