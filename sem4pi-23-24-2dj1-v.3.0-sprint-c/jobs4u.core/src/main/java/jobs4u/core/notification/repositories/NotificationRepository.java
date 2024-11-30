package jobs4u.core.notification.repositories;

import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.notification.domain.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    Notification findNotificationById(Long id);
    List<Notification> findNotificationsByRecipient(EmailAddress recipient);
    @Query("select notification from Notification notification where notification.recipient = :recipient and notification.read = false")
    List<Notification> findUnreadNotificationsForRecipient(@Param("recipient") EmailAddress recipient);
    @Query("select notification from Notification notification where notification.recipient = :recipient and notification.read = false and notification.time > :date")
    List<Notification> findUnreadNotificationsForRecipientAfterDate(@Param("recipient") EmailAddress recipient, @Param("date") LocalDateTime time);
    @Query("select notification from Notification notification where notification.recipient = :recipient and notification.time > :date")
    List<Notification> findAllNotificationsForRecipientAfterDate(@Param("recipient") EmailAddress recipient, @Param("date") LocalDateTime time);
}
