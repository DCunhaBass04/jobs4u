package jobs4u.app.backoffice.console.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.Console;

@Configuration
@EntityScan({"jobs4u.core.backoffice.domain", "jobs4u.core.candidate.domain",
"jobs4u.core.customer.domain", "jobs4u.core.interviewmodel.domain",
"jobs4u.core.jobapplication.domain", "jobs4u.core.jobopening.domain",
"jobs4u.core.jobrequirements.domain"})
@EnableJpaRepositories(basePackages = {"jobs4u.core.candidate.repositories", "jobs4u.core.customer.repositories",
        "jobs4u.core.jobapplication.repositories", "jobs4u.core.jobopening.repositories",
        "jobs4u.core.interviewmodel.repositories", "jobs4u.core.jobrequirements.repositories"})
@EnableTransactionManagement
public class BackofficeAppConfig {

    

}
