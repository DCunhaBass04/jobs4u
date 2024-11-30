package jobs4u.core.notification.dto;

import com.google.common.primitives.Longs;
import jobs4u.core.DTO;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class NotificationDTO implements DTO {
    private final Long id;
    private final String recipient;
    private final String title;
    private final String content;
    private final Long time;
    private final boolean read;
    public NotificationDTO(Long id, String recipient, String title, String content, LocalDateTime time, boolean read){
        this.id = id;
        this.recipient = recipient;
        this.title = title;
        this.content = content;
        String active = String.format("%04d%02d%02d%02d%02d", time.getYear(), time.getMonthValue(),
               time.getDayOfMonth(), time.getHour(), time.getMinute());
        this.time = Long.parseLong(active);
        this.read = read;
    }
    @Override
    public byte[] toBytes(){
        List<Byte> list = new ArrayList<>();
        for(byte b : Longs.toByteArray(id)) list.add(b);
        for(byte b : recipient.getBytes(StandardCharsets.US_ASCII)) list.add(b);
        for(byte b : title.getBytes(StandardCharsets.US_ASCII)) list.add(b);
        for(byte b : content.getBytes(StandardCharsets.US_ASCII)) list.add(b);
        for(byte b : Longs.toByteArray(time)) list.add(b);
        if (read) list.add((byte)1); else list.add((byte)0);
        byte[] array = new byte[list.size()];
        for(int i = 0; i < list.size(); i++) array[i] = list.get(i);
        return array;
    }
}
