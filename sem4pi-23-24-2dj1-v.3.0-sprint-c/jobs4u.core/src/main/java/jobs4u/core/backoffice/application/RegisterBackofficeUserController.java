package jobs4u.core.backoffice.application;

import eapli.framework.application.UseCaseController;
import jobs4u.infrastructure.auth.domain.Jobs4uPasswordPolicy;
import jobs4u.infrastructure.auth.services.Jobs4uUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UseCaseController
public class RegisterBackofficeUserController {

    @Autowired
    private Jobs4uUserManagementService registerService;

    @Autowired
    private Jobs4uPasswordPolicy jobs4uPasswordPolicy;

    public void registerUser(String role, String name, String password, String email){
        switch (role){
            case "CUSTOMER_MANAGER":
                registerService.registerCustomerManager(name,password,email,true);
                break;
            case "OPERATOR":
                registerService.registerOperator(name,password,email,true);
                break;
            case "LANGUAGE_ENGINEER":
                registerService.registerLanguageEngineer(name,password,email,true);
                break;
        }
    }

    public void passwordIsSatisfiedBy(String rawPassword) {
        jobs4uPasswordPolicy.PasswordIsSatisfiedBy(rawPassword);
    }

    public void emailIsSatisfiedBy(String rawEmail) {
        if (!rawEmail.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("\nInvalid email format. Email should be in the format: username@domain.com");
        }
    }
}
