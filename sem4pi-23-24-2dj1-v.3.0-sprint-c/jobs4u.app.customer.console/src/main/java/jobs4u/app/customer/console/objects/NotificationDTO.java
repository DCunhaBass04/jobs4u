package jobs4u.app.customer.console.objects;

import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
public class NotificationDTO {
    private final Long id;
    private final String recipient;
    private final String title;
    private final String content;
    private final Long time;
    private boolean read;
    public NotificationDTO(byte[] bytes){
        List<Byte> list = new ArrayList<>();
        int i = 0;
        byte[] result = new byte[8];
        for(int k=0;k<result.length;k++) result[k] = bytes[i++]; //id
        id = new BigInteger(result).longValue();
        while(bytes[i] != '0') list.add(bytes[i++]); //recipient
        i++;
        result = new byte[list.size()];
        for(int k=0;k<result.length;k++) result[k] = list.get(k);
        recipient = new String(result);
        list = new ArrayList<>();
        while(bytes[i] != '0') list.add(bytes[i++]); //title
        i++;
        result = new byte[list.size()];
        for(int k=0;k<result.length;k++) result[k] = list.get(k);
        title = new String(result);
        list = new ArrayList<>();
        while(bytes[i] != '0') list.add(bytes[i++]); //content
        i++;
        result = new byte[list.size()];
        for(int k=0;k<result.length;k++) result[k] = list.get(k);
        content = new String(result);
        result = new byte[8];
        for(int k=0;k<result.length;k++) result[k] = bytes[i++]; //id
        time = new BigInteger(result).longValue();
        read = bytes[i] != (byte)0;
    }
    public void read(){read = true;}
    public String toString(){
        StringBuilder dataOfCreation = new StringBuilder(String.valueOf(time));
        //202404041530
        dataOfCreation.insert(4, "/"); dataOfCreation.insert(7, "/");
        dataOfCreation.insert(10, " "); dataOfCreation.insert(13, ":");
        //2024/04/04 15:30
        return String.format("%s from %s%n%s%nState = %s%n", title, dataOfCreation, content, read ? "Read" : "Not read");
    }
}
