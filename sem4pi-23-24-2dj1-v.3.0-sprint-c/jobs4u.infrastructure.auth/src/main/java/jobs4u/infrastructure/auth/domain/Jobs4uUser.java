package jobs4u.infrastructure.auth.domain;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "JOBS4U_USER", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "email"})
})
public class Jobs4uUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Embedded
    @Column(name ="username", nullable = false)
    private Username username;

    @Embedded
    @Column(name = "password_encoded")
    private Password password;

    @Embedded
    @Column(name = "email", nullable = false)
    private EmailAddress email;

    @Enumerated(EnumType.STRING)
    private Jobs4uUserRoles role;

    private boolean  isEnabled;

    protected Jobs4uUser() {

    }

    public Jobs4uUser(Username username, Password password, EmailAddress email, Jobs4uUserRoles role, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        return "Jobs4uUser{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", email=" + email +
                ", role=" + role +
                ", isEnabled=" + isEnabled +
                '}';
    }

    public void toggleStatus(){isEnabled = !isEnabled;}
}
