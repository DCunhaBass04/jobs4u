package jobs4u.app.backoffice.console;

import jobs4u.app.backoffice.console.presentation.admin.menu.AdminMenu;
import jobs4u.app.backoffice.console.presentation.customermanager.menu.CustomerManagerMenu;
import jobs4u.app.backoffice.console.presentation.langengineer.menu.LangEngineerMenu;
import jobs4u.app.backoffice.console.presentation.operator.menu.OperatorMenu;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import jobs4u.infrastructure.auth.ui.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"jobs4u.infrastructure.auth", "jobs4u.app.backoffice.console", "jobs4u.core"})
public class BackofficeApp implements CommandLineRunner {

    @Autowired
    private Jobs4uUserAuthService authService;

    @Autowired
    private MainMenu mainMenu;

    @Autowired
    private AdminMenu adminMenu;

    @Autowired
    private CustomerManagerMenu customerManagerMenu;

    @Autowired
    private LangEngineerMenu langEngineerMenu;

    @Autowired
    private OperatorMenu operatorMenu;

    public static void main(String[] args) {
        SpringApplication.run(BackofficeApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainMenu.show();
        if (authService.authenticatedUser() != null) {
            switch (authService.authenticatedUser().getRole()) {
                case ADMIN -> adminMenu.show();
                case CUSTOMER_MANAGER -> customerManagerMenu.show();
                case LANGUAGE_ENGINEER -> langEngineerMenu.show();
                case OPERATOR -> operatorMenu.show();
                default -> System.out.println("User does not have a valid role for this App");
            }
        }
    }
}
