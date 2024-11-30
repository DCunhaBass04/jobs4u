package jobs4u.core.jobapplication.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@Getter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ApplicationFile implements ValueObject {

    private String filepath;

    @Enumerated(EnumType.STRING)
    private ApplicationFileFunction fileFunction;

    public ApplicationFile(String filepath, ApplicationFileFunction fileFunction) {
        if (Strings.isBlank(filepath) || Strings.isBlank(String.valueOf(fileFunction))) {
            throw new IllegalArgumentException("Application filepath can't be empty.");
        }
        this.filepath = filepath;
        this.fileFunction = fileFunction;
    }
    public String path(){return filepath;}
    public String toString(){return String.format("%s file from %s%n", fileFunction, filepath);}
}
