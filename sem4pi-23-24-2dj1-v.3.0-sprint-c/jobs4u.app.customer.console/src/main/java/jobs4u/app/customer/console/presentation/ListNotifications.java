package jobs4u.app.customer.console.presentation;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.app.customer.console.messages.*;
import jobs4u.app.customer.console.objects.NotificationDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.*;

public class ListNotifications extends AbstractUI {
    private final Socket socket;
    private final String email;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    private static final byte VERSION = 1;
    public ListNotifications(Socket sock, String email){
        socket = sock;
        this.email = email;
    }
    @Override
    protected boolean doShow() {
        List<NotificationDTO> dtos;

        try {
            sOut = new DataOutputStream(socket.getOutputStream());
            sIn = new DataInputStream(socket.getInputStream());
            System.out.printf("Select an option:%n1 - View all notifications%n2 - View all unread notifications%n3 - View all notifications after certain date%n");
            int option = Console.readOption(1,3,0);
            switch(option){
                case 0: return false;
                case 3:
                    dtos = getListOfNotifications(email, readDate());
                    break;
                default:
                    dtos = getListOfNotifications(email, (long) option);
                    break;
            }
            if (dtos.isEmpty()) {
                System.out.println("You have no notifications");
                return false;
            }
            readNotifications(dtos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
    private List<NotificationDTO> getListOfNotifications(String email, Object option) throws IOException {
        doRequest(email, option);
        MessageFormat format = MessageReader.readMessage(sIn);
        List<NotificationDTO> dtos = new ArrayList<>();
        assert format != null;
        for(MessageData data : format.getDatas()) dtos.add(new NotificationDTO(data.getData()));
        return dtos;
    }
    private void doRequest(String email, Object option) throws IOException {
        Long value;
        if(option instanceof LocalDateTime dateTime) {
            String active = String.format("%04d%02d%02d%02d%02d", dateTime.getYear(), dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),dateTime.getHour(),dateTime.getMinute());
            value = Long.valueOf(active);
        } else value = (Long) option;

        List<Byte> bytes = new ArrayList<>();
        bytes.add(VERSION);
        bytes.add(MessageCode.GETNOTIFS.getValue());

        MessageEncoder.addBytesFromString(bytes, email);
        //bytes.add((byte)0);

        MessageEncoder.addBytesFromLong(bytes, value);
        bytes.add((byte)0); bytes.add((byte)0);

        sOut.write(MessageReader.getArrayOfBytes(bytes));
    }
    private void readNotifications(List<NotificationDTO> dtos) throws IOException {
        int option;
        while(true){
            System.out.printf("%nPick a notification to read (0 to exit)%n");
            for(int i = 0; i < dtos.size(); i++) System.out.printf("%d - %s - %s%n", (i+1), dtos.get(i).getTitle(), dtos.get(i).isRead() ? "Read" : "Not read");
            option = Console.readOption(1,dtos.size(),0);
            if(option == 0) return;
            System.out.println(dtos.get(option-1).getContent());
            if(!dtos.get(option-1).isRead()){
                List<Byte> bytes = new ArrayList<>();
                bytes.add(VERSION);
                bytes.add(MessageCode.READNOTIF.getValue());
                MessageEncoder.addBytesFromLong(bytes, dtos.get(option-1).getId());
                bytes.add((byte)0); bytes.add((byte)0);
                sOut.write(MessageReader.getArrayOfBytes(bytes));
                MessageFormat format = MessageReader.readMessage(sIn);
                dtos.get(option-1).read();
                assert format != null;
                if(format.getCode().equals(MessageCode.ERR)) return;
            }
        }
    }
    private LocalDateTime readDate(){
        int year, month, day, hour, minute;
        year = Console.readInteger("Insert a year");
        month = Console.readInteger("Insert a month (numerical value)");
        day = Console.readInteger("Insert the day of the month");
        hour = Console.readInteger("Insert an hour");
        minute = Console.readInteger("Insert a minute");
        return LocalDateTime.of(year,month,day,hour,minute);
    }
    @Override
    public String headline() {
        return "Read Notifications";
    }
}
