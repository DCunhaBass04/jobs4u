package jobs4u.followup.server.messages;

import jobs4u.core.DTO;
import jobs4u.core.jobopening.dto.JobOpeningSmallDTO;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
public class MessageData {
    private byte dataLenL;
    private byte dataLenM;
    private byte[] data;
    public MessageData(byte dataLenL, byte dataLenM, byte[] data){
        this.dataLenL = dataLenL;
        this.dataLenM = dataLenM;
        this.data = data;
    }
    public MessageData(String data){
        short size = (short)data.length();
        dataLenL = (byte) (size%256);
        dataLenM = (byte) (size/256);
        this.data = data.getBytes(StandardCharsets.US_ASCII);
    }
    public MessageData(DTO dto){
        this.data = dto.toBytes();
        short size = (short)data.length;
        dataLenL = (byte) (size%256);
        dataLenM = (byte) (size/256);
    }
}
