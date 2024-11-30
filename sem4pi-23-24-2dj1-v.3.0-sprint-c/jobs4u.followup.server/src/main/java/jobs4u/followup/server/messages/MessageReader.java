package jobs4u.followup.server.messages;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageReader {
    public static MessageFormat readMessage(DataInputStream sIn) {
        List<Byte> bytes = new ArrayList<>();
        try {
            int i;
            for(i = 0; i < 4; i++) {
                bytes.add(sIn.readByte());
            }
            short size;
            int size1 = bytes.get(i-2) & 0xFF, size2 = bytes.get(i-1) & 0xFF;
            size = (short) (size1 + 256 * size2);
            while(size != 0) {
                for(short s = 0; s < size + 2; s++) {
                    bytes.add(sIn.readByte());
                    i++;
                }
                size1 = bytes.get(i-2) & 0xFF; size2 = bytes.get(i-1) & 0xFF;
                size = (short) (size1 + 256 * size2);
            }
            return new MessageFormat(getArrayOfBytes(bytes));
        }
        catch(IOException ex) { System.out.println("IOException"); }
        return null;
    }
    public static byte[] getArrayOfBytes(List<Byte> list){
        byte[] result = new byte[list.size()];
        for(int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
