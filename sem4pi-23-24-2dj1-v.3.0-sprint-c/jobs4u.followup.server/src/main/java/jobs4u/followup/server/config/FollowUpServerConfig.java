package jobs4u.followup.server.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan({"jobs4u.core.backoffice.domain", "jobs4u.core.candidate.domain",
"jobs4u.core.customer.domain", "jobs4u.core.interviewmodel.domain",
"jobs4u.core.jobapplication.domain", "jobs4u.core.jobopening.domain",
"jobs4u.core.jobrequirements.domain", "jobs4u.core.notification.domain"})
@EnableJpaRepositories(basePackages = {"jobs4u.core.candidate.repositories", "jobs4u.core.customer.repositories",
        "jobs4u.core.jobapplication.repositories", "jobs4u.core.jobopening.repositories",
        "jobs4u.core.interviewmodel.repositories", "jobs4u.core.jobrequirements.repositories",
        "jobs4u.core.notification.repositories"})
@EnableTransactionManagement
public class FollowUpServerConfig {

    

}
