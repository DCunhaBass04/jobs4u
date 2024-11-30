package jobs4u.core.notification.domain;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.core.notification.dto.NotificationDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notification implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    @Column(name = "RECIPIENT")
    private EmailAddress recipient;
    private String title;
    private String content;
    private LocalDateTime time;
    @Column(name = "isread")
    private Boolean read;
    public Notification(EmailAddress recipient, String title, String content, LocalDateTime time){
        Preconditions.noneNull(recipient, title, content, time);

        this.recipient = recipient;
        this.title = title;
        this.content = content;
        this.time = time;
        read = false;
    }
    public void readNotification(){read = true;}
    @Override
    public boolean sameAs(Object other) {return Objects.equals(this.id, ((Notification) other).id);}
    @Override
    public Long identity() {return id;}
    public NotificationDTO toDto(){return new NotificationDTO(id, recipient.toString()+'0', title+'0', content+'0', time, read);}
}
