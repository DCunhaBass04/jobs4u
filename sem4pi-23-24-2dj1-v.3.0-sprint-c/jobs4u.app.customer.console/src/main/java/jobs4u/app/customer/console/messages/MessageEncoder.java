package jobs4u.app.customer.console.messages;

import com.google.common.primitives.Longs;

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
    public static void addBytesFromLong(List<Byte> list, Long value){
        list.add((byte)8); list.add((byte)0);
        byte[] bytes = Longs.toByteArray(value);
        for(byte character : bytes) list.add(character);
    }
}
