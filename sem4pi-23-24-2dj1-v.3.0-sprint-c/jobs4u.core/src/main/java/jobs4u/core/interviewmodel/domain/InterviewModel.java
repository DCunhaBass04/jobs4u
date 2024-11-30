package jobs4u.core.interviewmodel.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;
import jobs4u.core.interviewmodel.services.InterviewModelEvaluator;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewModel implements AggregateRoot<InterviewModelDesignation> {

    @EmbeddedId
    @Column(name = "INTERVIEW_MODEL_ID")
    private InterviewModelDesignation interviewModelDesignation;

   // private InterviewModelDescription interviewModelDescription;  Colocar isto aqui certo?

    private String className;
    private String jarFilePath;

    public InterviewModel(InterviewModelDesignation interviewModelDesignation, String className, String jarfile) {
        if (Strings.isBlank(className) || Strings.isBlank(String.valueOf(interviewModelDesignation))) {
            throw new IllegalArgumentException();
        }
        this.interviewModelDesignation = interviewModelDesignation;
        this.className = className;
        this.jarFilePath = jarfile;
    }

    public InterviewModelEvaluator buildEvaluator() {
        try {
            return (InterviewModelEvaluator) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException
                 | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            throw new IllegalStateException("Unable to dynamically load the Plugin: " + className, ex);
        }
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public InterviewModelDesignation identity() {
        return interviewModelDesignation;
    }


    public String toString(){return String.format("Designation = %s%nClasName = %s%n",interviewModelDesignation,className);}

    public Class<?> getClassFromPlugin() throws MalformedURLException, URISyntaxException, ClassNotFoundException {
        URL myJar = (new File(jarFilePath)).toURI().toURL();
        URLClassLoader child = new URLClassLoader(
                new URL[] {myJar.toURI().toURL()},
                this.getClass().getClassLoader()
        );
        return Class.forName(className, true, child);
    }
}