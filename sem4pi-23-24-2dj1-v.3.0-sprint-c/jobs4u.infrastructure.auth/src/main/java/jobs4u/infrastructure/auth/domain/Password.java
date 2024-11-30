package jobs4u.infrastructure.auth.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import jakarta.persistence.Embeddable;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
public class Password implements ValueObject {

    private String value;

    protected Password() {

    }

    protected Password(String value) {
        this.value = value;
    }

    /**
     * Encode and validate password password.
     *
     * @param rawPawword      the raw pawword
     * @param passwordPolicy  the password policy at least 8 characters, one capital, one digit and one special character.
     * @param passwordEncoder the password encoder
     * @return the password
     */
    public static Password encodeAndValidatePassword(String rawPawword, PasswordPolicy passwordPolicy, PasswordEncoder passwordEncoder) {
        if (!passwordPolicy.isSatisfiedBy(rawPawword)) {
            throw new RuntimeException("Password does not satisfy policy");
        }
        return new Password(passwordEncoder.encode(rawPawword));
    }

    public String value() {
        return value;
    }

}
