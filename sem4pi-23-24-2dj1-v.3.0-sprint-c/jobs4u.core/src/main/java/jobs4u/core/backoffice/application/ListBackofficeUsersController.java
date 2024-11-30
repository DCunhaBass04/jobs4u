package jobs4u.core.backoffice.application;

import eapli.framework.application.UseCaseController;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import jobs4u.infrastructure.auth.repositories.Jobs4uUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@UseCaseController
public class ListBackofficeUsersController {

    @Autowired
    private Jobs4uUserRepository userRepository;

    public List<Jobs4uUser> getBackofficeUsers() {
        return userRepository.backofficeUsers();
    }

}
