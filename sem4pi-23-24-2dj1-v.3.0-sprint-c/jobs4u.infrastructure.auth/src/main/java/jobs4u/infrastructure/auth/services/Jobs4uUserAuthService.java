package jobs4u.infrastructure.auth.services;

import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.infrastructure.auth.domain.Jobs4uPasswordPolicy;
import jobs4u.infrastructure.auth.domain.Password;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import jobs4u.infrastructure.auth.repositories.Jobs4uUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Esta classe de serviço serve o propósito de fornecer serviços básicos
 * de autenticação e autorização para o sistema da Jobs4U.
 */

@Service
public class Jobs4uUserAuthService {

    @Autowired
    private Jobs4uUserRepository jobs4uUserRepository;

    @Autowired
    private Jobs4uPasswordPolicy passwordPolicy;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Jobs4uUser authenticatedUser = null;

    @Transactional
    public boolean authenticate(String rawuUsername, String rawPassword) {
        Username username = Username.valueOf(rawuUsername);
        boolean isAuthenticated = false;
        Jobs4uUser user = jobs4uUserRepository.findJobs4uUserByUsername(username);
        if (user != null && user.isEnabled() && passwordEncoder.matches(rawPassword, user.getPassword().value())) {
            isAuthenticated = true;
            authenticatedUser = user;
        }
        return isAuthenticated;
    }

    @Transactional
    public Jobs4uUser authenticatedUser() {
        return authenticatedUser;
    }

    public void clearSession() {
        authenticatedUser = null;
    }

    public boolean passwordValid(String rawPassword) {
        return passwordPolicy.isSatisfiedBy(rawPassword);
    }

}
