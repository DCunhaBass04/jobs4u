package jobs4u.app.language.grammar;

import jobs4u.app.language.grammar.presentation.languagePlugin.menu.LanguagePluginMenu;
import jobs4u.infrastructure.auth.domain.Jobs4uUserRoles;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import jobs4u.infrastructure.auth.ui.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Objects;

@SpringBootApplication
@ComponentScan(basePackages = {"jobs4u.infrastructure.auth", "jobs4u.app.language.grammar", "jobs4u.core"})
public class LanguageApp implements CommandLineRunner {

    @Autowired
    private Jobs4uUserAuthService authService;

    @Autowired
    private MainMenu mainMenu;

    @Autowired
    private LanguagePluginMenu languagePluginMenu;

    public static void main(String[] args) {
        SpringApplication.run(LanguageApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainMenu.show();
        System.out.println("--------------------THIS MENU IS PURELY FOR TESTING--------------------");
        if (authService.authenticatedUser() != null) {
            if (Objects.requireNonNull(authService.authenticatedUser().getRole()) == Jobs4uUserRoles.LANGUAGE_ENGINEER)
                languagePluginMenu.show();
            else System.out.println("User does not have a valid role for this App");
        }
    }
}
