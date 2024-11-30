package jobs4u.infrastructure.bootstrappers.app;

import jobs4u.infrastructure.bootstrappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"jobs4u.infrastructure.auth", "jobs4u.infrastructure.bootstrappers", "jobs4u.core"})
@EntityScan({"jobs4u.core.backoffice.domain", "jobs4u.core.candidate.domain",
        "jobs4u.core.customer.domain", "jobs4u.core.interviewmodel.domain",
        "jobs4u.core.jobapplication.domain", "jobs4u.core.jobopening.domain",
        "jobs4u.core.jobrequirements.domain", "jobs4u.core.notification.domain"})
@EnableJpaRepositories(basePackages = {"jobs4u.core.candidate.repositories",
        "jobs4u.core.customer.repositories", "jobs4u.core.jobapplication.repositories", "jobs4u.core.jobopening.repositories",
        "jobs4u.core.interviewmodel.repositories", "jobs4u.core.jobrequirements.repositories", "jobs4u.core.notification.repositories"})
@EnableTransactionManagement
public class BootstrapApp implements CommandLineRunner {

    @Autowired
    private BackofficeBootstrapper backofficeBootstrapper;

    @Autowired
    private CandidateBootstrapper candidateBootstrapper;

    @Autowired
    private CustomerBootstrapper customerBootstrapper;

    @Autowired
    private InterviewModelBootstrapper interviewModelBootstrapper;

    @Autowired
    private JobRequirementsBootstrapper jobRequirementsBootstrapper;

    @Autowired
    private JobOpeningBootstrapper jobOpeningBootstrapper;

    @Autowired
    private JobApplicationBootstrapper jobApplicationBootstrapper;

    @Autowired
    private NotificationBootstrapper notificationBootstrapper;


    public static void main(String[] args) {
        SpringApplication.run(BootstrapApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        backofficeBootstrapper.bootstrap();
        customerBootstrapper.bootstrap();
        candidateBootstrapper.bootstrap();
        interviewModelBootstrapper.bootstrap();
        jobRequirementsBootstrapper.bootstrap();
        jobOpeningBootstrapper.bootstrap();
        jobApplicationBootstrapper.bootstrap();
        notificationBootstrapper.bootstrap();
    }
}
