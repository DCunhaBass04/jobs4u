package jobs4u.app.customer.console.messages;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MessageFormat {
    private byte version;
    private MessageCode code;
    private List<MessageData> datas;
    //public MessageFormat(byte version, MessageCode code, List<MessageData> datas){
    //    this.version = version;
    //    this.code = code;
    //    this.datas = datas;
    //}
    public MessageFormat(byte version, MessageCode code, List<String> infos){
        this.version = version;
        this.code = code;
        datas = new ArrayList<>();
        for(String info : infos) datas.add(new MessageData(info));
    }
    public MessageFormat(byte[] bytes){
        version = bytes[0];
        code = MessageCode.getName(bytes[1]);
        datas = new ArrayList<>();
        byte dataL = bytes[2], dataM = bytes[3];
        short size, i = 4;
        byte[] struct;
        int size1, size2;
        while(dataL != (byte)0 || dataM != (byte)0){
            size1 = dataL & 0xFF; size2 = dataM & 0xFF;
            size = (short) (size1 + 256 * size2);
            struct = new byte[size];
            for(short j = 0; j < size; j++){
                struct[j] = bytes[i++];
            }
            datas.add(new MessageData(dataL, dataM, struct));
            dataL = bytes[i++]; dataM = bytes[i++];
        }
    }
    public byte[] toBytes(){
        List<Byte> list = new ArrayList<>();
        list.add(version); list.add(code.getValue());
        for(MessageData data : datas){
            list.add(data.getDataLenL());
            list.add(data.getDataLenM());
            for(byte b : data.getData()) list.add(b);
        }
        list.add((byte)0); list.add((byte)0);
        return getArrayOfBytes(list);
    }
    private byte[] getArrayOfBytes(List<Byte> list){
        byte[] result = new byte[list.size()];
        for(int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
