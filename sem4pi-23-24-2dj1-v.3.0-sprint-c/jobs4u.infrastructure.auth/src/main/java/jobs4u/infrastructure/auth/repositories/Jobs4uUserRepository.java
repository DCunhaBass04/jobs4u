package jobs4u.infrastructure.auth.repositories;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jakarta.persistence.NamedQueries;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;

public interface Jobs4uUserRepository extends ListCrudRepository<Jobs4uUser, Username> {

    Jobs4uUser findJobs4uUserByUsername(Username username);

    Jobs4uUser findJobs4uUserByEmail(EmailAddress email);

    @Query("select user from Jobs4uUser user where user.role = 'ADMIN' or user.role = 'CUSTOMER_MANAGER' or user.role = 'OPERATOR' or user.role = 'LANGUAGE_ENGINEER'")
    List<Jobs4uUser> backofficeUsers();


}
