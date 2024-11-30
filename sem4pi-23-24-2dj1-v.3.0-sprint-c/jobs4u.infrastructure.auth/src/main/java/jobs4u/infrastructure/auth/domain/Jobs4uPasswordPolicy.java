package jobs4u.infrastructure.auth.domain;

import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.strings.util.StringPredicates;
import org.springframework.stereotype.Component;

@Component
public class Jobs4uPasswordPolicy implements PasswordPolicy {

    @Override
    public boolean isSatisfiedBy(String rawPassword) {
        if (StringPredicates.isNullOrEmpty(rawPassword)) {
            return false;
        }
        if (rawPassword.length() < 6) {
            return false;
        }
        if (!StringPredicates.containsDigit(rawPassword)) {
            return false;
        }
        return StringPredicates.containsCapital(rawPassword);
    }

    public void PasswordIsSatisfiedBy(String rawPassword) {
        boolean password = isSatisfiedBy(rawPassword);
        if(password == false) {
            throw new IllegalArgumentException("\nInvalid password format. Password should be at least 7 characters long, contain at least one capital letter and one number");
        }
    }

}
