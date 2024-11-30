package jobs4u.followup.server;

import jobs4u.followup.server.actions.FollowUpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"jobs4u.infrastructure.auth", "jobs4u.followup.server", "jobs4u.core"})
public class FollowupServerApp implements CommandLineRunner {
    @Autowired
    private FollowUpServer server;

    public static void main(String[] args) {
        SpringApplication.run(FollowupServerApp.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        server.run();
    }
}
