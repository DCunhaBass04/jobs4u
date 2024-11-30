package jobs4u.core.jobrequirements.application;

import eapli.framework.application.UseCaseController;
import jobs4u.core.jobopening.domain.JobOpening;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import jobs4u.core.jobrequirements.repositories.JobRequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Scanner;

@Component
@UseCaseController
public class ExportRequirementsTemplateFileController {

    @Autowired
    private JobOpeningRepository jobOpeningRepository;

    public void getTemplate(JobRequirements jobRequirements) throws IOException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, URISyntaxException {
        File jarFile = new File(jobRequirements.getJarFilePath());

        String className = jobRequirements.getClassName();
        URL myJar = jarFile.toURI().toURL();
        URLClassLoader child = new URLClassLoader(
                new URL[] {myJar.toURI().toURL()},
                this.getClass().getClassLoader()
        );

        Class<?> classToLoad = Class.forName(className, true, child);
        exportTemplate(classToLoad);
    }
    private void exportTemplate(Class<?> cls) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method method = cls.getDeclaredMethod("exportTemplate");
        Object instance = cls.newInstance();
        System.out.println(method.invoke(instance));
    }

    public List<JobOpening> getJobOpenings(){
        return jobOpeningRepository.findAll();
    }

    public void getAndExportTemplate(JobOpening jobOpening) throws IOException, URISyntaxException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JobRequirements jobRequirements = jobOpening.getJobRequirements();
        getTemplate(jobRequirements);
    }
}