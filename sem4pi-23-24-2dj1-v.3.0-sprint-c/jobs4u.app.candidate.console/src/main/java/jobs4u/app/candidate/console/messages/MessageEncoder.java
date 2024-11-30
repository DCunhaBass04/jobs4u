package jobs4u.app.candidate.console.messages;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageEncoder {
    public static void addBytesFromString(List<Byte> list, String word){
        short size = (short) word.length();
        list.add((byte) (size%256));
        list.add((byte) (size/256));

        byte[] bytes = word.getBytes(StandardCharsets.US_ASCII);
        for(byte character : bytes) list.add(character);
    }
}
