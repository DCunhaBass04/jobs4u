package jobs4u.core.backoffice.application;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import jobs4u.infrastructure.auth.repositories.Jobs4uUserRepository;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UseCaseController
public class DisableBackofficeUserController {

    @Autowired
    private Jobs4uUserAuthService authService;

    @Autowired
    private Jobs4uUserRepository userRepository;

    public List<Jobs4uUser> getBackofficeUsers() {
        return userRepository.backofficeUsers();
    }

    public void findUserAndToggleStatus(String email){
        Jobs4uUser user = userRepository.findJobs4uUserByEmail(EmailAddress.valueOf(email));
        toggleUserStatus(user);
    }

    public void toggleUserStatus(Jobs4uUser user){
        user.toggleStatus();
        userRepository.save(user);
    }

    public void emailIsSatisfiedBy(String rawEmail) {
        if (!rawEmail.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("\nInvalid email format. Email should be in the format: username@domain.com");
        }
    }

}
