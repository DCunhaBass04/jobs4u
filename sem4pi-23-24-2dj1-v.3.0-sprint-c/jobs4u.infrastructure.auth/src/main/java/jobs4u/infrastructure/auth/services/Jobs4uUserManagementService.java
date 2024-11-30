package jobs4u.infrastructure.auth.services;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.infrastructure.auth.domain.Jobs4uPasswordPolicy;
import jobs4u.infrastructure.auth.domain.Jobs4uUser;
import jobs4u.infrastructure.auth.domain.Password;
import jobs4u.infrastructure.auth.domain.Jobs4uUserRoles;
import jobs4u.infrastructure.auth.repositories.Jobs4uUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Esta classe de serviço auxilia com operações basicas de
 * gestão dos utilizadores da Jobs4u.
 */

@Service
public class Jobs4uUserManagementService {

    @Autowired
    private Jobs4uUserRepository jobs4uUserRepository;

    @Autowired
    private Jobs4uPasswordPolicy passwordPolicy;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerAdmin(String rawUsername, String rawPassword, String rawEmail, boolean isEnabled) {
        Username username = Username.valueOf(rawUsername);
        Password password = Password.encodeAndValidatePassword(rawPassword, passwordPolicy,passwordEncoder);
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Jobs4uUserRoles role = Jobs4uUserRoles.ADMIN;
        Jobs4uUser user = new Jobs4uUser(username, password, email, role, isEnabled);
        jobs4uUserRepository.save(user);
    }

    @Transactional
    public void registerCustomerManager(String rawUsername, String rawPassword, String rawEmail, boolean isEnabled) {
        Username username = Username.valueOf(rawUsername);
        Password password = Password.encodeAndValidatePassword(rawPassword, passwordPolicy,passwordEncoder);
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Jobs4uUserRoles role = Jobs4uUserRoles.CUSTOMER_MANAGER;
        Jobs4uUser user = new Jobs4uUser(username, password, email, role, isEnabled);
        jobs4uUserRepository.save(user);
    }
    @Transactional
    public void registerOperator(String rawUsername, String rawPassword, String rawEmail, boolean isEnabled) {
        Username username = Username.valueOf(rawUsername);
        Password password = Password.encodeAndValidatePassword(rawPassword, passwordPolicy,passwordEncoder);
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Jobs4uUserRoles role = Jobs4uUserRoles.OPERATOR;
        Jobs4uUser user = new Jobs4uUser(username, password, email, role, isEnabled);
        jobs4uUserRepository.save(user);
    }

    @Transactional
    public void registerLanguageEngineer(String rawUsername, String rawPassword, String rawEmail, boolean isEnabled) {
        Username username = Username.valueOf(rawUsername);
        Password password = Password.encodeAndValidatePassword(rawPassword, passwordPolicy,passwordEncoder);
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Jobs4uUserRoles role = Jobs4uUserRoles.LANGUAGE_ENGINEER;
        Jobs4uUser user = new Jobs4uUser(username, password, email, role, isEnabled);
        jobs4uUserRepository.save(user);
    }

    @Transactional
    public void registerCustomer(String rawUsername, String rawPassword, String rawEmail, boolean isEnabled) {
        Username username = Username.valueOf(rawUsername);
        Password password = Password.encodeAndValidatePassword(rawPassword, passwordPolicy,passwordEncoder);
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Jobs4uUserRoles role = Jobs4uUserRoles.CUSTOMER;
        Jobs4uUser user = new Jobs4uUser(username, password, email, role, isEnabled);
        jobs4uUserRepository.save(user);
    }

    @Transactional
    public void registerCandidate(String rawUsername, String rawPassword, String rawEmail, boolean isEnabled) {
        Username username = Username.valueOf(rawUsername);
        Password password = Password.encodeAndValidatePassword(rawPassword, passwordPolicy,passwordEncoder);
        EmailAddress email = EmailAddress.valueOf(rawEmail);
        Jobs4uUserRoles role = Jobs4uUserRoles.CANDIDATE;
        Jobs4uUser user = new Jobs4uUser(username, password, email, role, isEnabled);
        jobs4uUserRepository.save(user);
    }

}
