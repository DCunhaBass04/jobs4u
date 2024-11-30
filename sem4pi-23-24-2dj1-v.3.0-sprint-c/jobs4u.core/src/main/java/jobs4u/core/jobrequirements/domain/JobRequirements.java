package jobs4u.core.jobrequirements.domain;

import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobRequirements implements AggregateRoot<JobRequirementsDesignation> {

    @EmbeddedId
    @Column(name = "DESIGNATION")
    private JobRequirementsDesignation jobRequirementsDesignation;

    /**
       name of the Class which implements the Interface of the Service:
      As for having Interview Model: “5 anos experiencia java”,"  Class name would be “req-model-5-years-java.jar”).
     */

    private String className;
    private String jarFilePath;

    public JobRequirements(JobRequirementsDesignation jobRequirementsDesignation, String className, String jarFilePath) {
        if (Strings.isBlank(className) || Strings.isBlank(String.valueOf(jobRequirementsDesignation)) || jobRequirementsDesignation == null ||
        Strings.isBlank(jarFilePath))
            throw new IllegalArgumentException();
        this.jobRequirementsDesignation = jobRequirementsDesignation;
        this.className = className;
        this.jarFilePath = jarFilePath;
    }
    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public JobRequirementsDesignation identity() {
        return null;
    }

    public Class<?> getClassFromPlugin() throws MalformedURLException, URISyntaxException, ClassNotFoundException {
        URL myJar = (new File(jarFilePath)).toURI().toURL();
        URLClassLoader child = new URLClassLoader(
                new URL[] {myJar.toURI().toURL()},
                this.getClass().getClassLoader()
        );
        return Class.forName(className, true, child);
    }
    @Override
    public String toString(){return String.format("Designation = %s%n\t Class Name = %s%n",jobRequirementsDesignation,className);}
}
