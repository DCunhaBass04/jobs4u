package jobs4u.infrastructure.bootstrappers;

import jobs4u.infrastructure.auth.services.Jobs4uUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackofficeBootstrapper {

    @Autowired
    Jobs4uUserManagementService registerService;

    public void bootstrap() {
        String rawPassword = "Password1";
        registerService.registerAdmin("admin", rawPassword, "admin@jobs4u.com", true);
        registerService.registerCustomerManager("customermanager", rawPassword, "customermanager@jobs4u.com", true);
        registerService.registerOperator("operator", rawPassword, "operator@jobs4u.com", true);
        registerService.registerLanguageEngineer("langengineer", rawPassword, "langengineer@jobs4u.com", true);
    }
}
