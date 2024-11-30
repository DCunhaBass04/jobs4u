package jobs4u.infrastructure.auth.config;

import eapli.framework.presentation.console.AbstractUI;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.Console;
import java.util.ArrayList;


@Configuration
@EntityScan({"jobs4u.infrastructure.auth.domain"})
@EnableJpaRepositories(basePackages = "jobs4u.infrastructure.auth.repositories")
@EnableTransactionManagement
public class AuthConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ArrayList<AbstractUI> arrayList() {
        return new ArrayList<>();
    }

}
