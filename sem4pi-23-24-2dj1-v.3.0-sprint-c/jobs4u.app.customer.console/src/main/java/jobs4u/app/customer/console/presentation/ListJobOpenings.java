package jobs4u.app.customer.console.presentation;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.app.customer.console.messages.*;
import jobs4u.app.customer.console.objects.JobOpeningSmallDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ListJobOpenings extends AbstractUI {
    private final Socket socket;
    private final String email;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    private static final byte VERSION = 1;
    public ListJobOpenings(Socket sock, String email) throws IOException {
        this.socket = sock;
        this.email = email;
    }
    @Override
    protected boolean doShow() {
        List<JobOpeningSmallDTO> dtos;
        try {
            sOut = new DataOutputStream(socket.getOutputStream());
            sIn = new DataInputStream(socket.getInputStream());
            dtos = getListOfJobOpenings(email);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        if (dtos.isEmpty()) System.out.println("You have no active job openings");
        else for(JobOpeningSmallDTO dto : dtos) System.out.println(dto);
        return false;
    }
    private List<JobOpeningSmallDTO> getListOfJobOpenings(String email) throws IOException {
        doRequest(email);
        MessageFormat format = MessageReader.readMessage(sIn);
        List<JobOpeningSmallDTO> dtos = new ArrayList<>();
        assert format != null;
        for(MessageData data : format.getDatas()) dtos.add(new JobOpeningSmallDTO(data.getData()));
        return dtos;
    }
    private void doRequest(String email) throws IOException {
        List<Byte> bytes = new ArrayList<>();
        bytes.add(VERSION);
        bytes.add(MessageCode.GETJOBOP.getValue());

        MessageEncoder.addBytesFromString(bytes, email);
        bytes.add((byte)0); bytes.add((byte)0);

        sOut.write(MessageReader.getArrayOfBytes(bytes));
    }
    @Override
    public String headline() {
        return "List Job Openings";
    }
}
