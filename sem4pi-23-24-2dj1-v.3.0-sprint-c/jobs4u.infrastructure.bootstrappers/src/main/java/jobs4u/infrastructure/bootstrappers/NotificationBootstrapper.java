package jobs4u.infrastructure.bootstrappers;

import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.notification.domain.Notification;
import jobs4u.core.notification.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationBootstrapper {
    @Autowired
    private NotificationRepository repository;
    public void bootstrap(){
        repository.save(new Notification(EmailAddress.valueOf("customer@jobs4u.com"), "Change on Job Opening phase", "The phase on one of your Job Openings (IBM-2) changed from APPLICATIONS to SCREENING", LocalDateTime.now()));
        repository.save(new Notification(EmailAddress.valueOf("customer@jobs4u.com"), "Change on Job Opening phase", "The phase on one of your Job Openings (IBM-1) changed from ANALYSIS to RESULT", LocalDateTime.now()));
    }
}
