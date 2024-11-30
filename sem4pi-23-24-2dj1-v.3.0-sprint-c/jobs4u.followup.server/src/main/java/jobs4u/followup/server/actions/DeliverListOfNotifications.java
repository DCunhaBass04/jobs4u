package jobs4u.followup.server.actions;

import com.google.common.primitives.Longs;
import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.notification.domain.Notification;
import jobs4u.core.notification.dto.NotificationDTO;
import jobs4u.core.notification.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeliverListOfNotifications {
    @Autowired
    private NotificationRepository notificationRepository;
    public List<NotificationDTO> getListOfDTO(String email, long value){
        List<Notification> notifications = switch ((int) value) {
            case 1 -> notificationRepository.findNotificationsByRecipient(EmailAddress.valueOf(email));
            case 2 -> notificationRepository.findUnreadNotificationsForRecipient(EmailAddress.valueOf(email));
            default -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
                yield notificationRepository.findAllNotificationsForRecipientAfterDate(EmailAddress.valueOf(email), LocalDateTime.parse(String.valueOf(value), formatter));
            }
        };
        List<NotificationDTO> dtos = new ArrayList<>();
        for(Notification notification : notifications) dtos.add(notification.toDto());
        return dtos;
    }

    @Transactional
    public void markNotificationAsRead(Long id){
        Notification notif = notificationRepository.findNotificationById(id);
        notif.readNotification();
        notificationRepository.save(notif);
    }
}
