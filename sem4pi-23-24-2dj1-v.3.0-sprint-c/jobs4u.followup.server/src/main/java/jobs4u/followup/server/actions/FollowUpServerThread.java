package jobs4u.followup.server.actions;

import com.google.common.primitives.Longs;
import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.candidate.repositories.CandidateRepository;
import jobs4u.core.jobapplication.domain.JobApplication;
import jobs4u.core.jobapplication.dto.JobApplicationSmallDTO;
import jobs4u.core.jobopening.dto.JobOpeningSmallDTO;
import jobs4u.core.notification.dto.NotificationDTO;
import jobs4u.followup.server.messages.MessageCode;
import jobs4u.followup.server.messages.MessageData;
import jobs4u.followup.server.messages.MessageFormat;
import jobs4u.followup.server.messages.MessageReader;
import jobs4u.infrastructure.auth.services.Jobs4uUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

@Component
public class FollowUpServerThread implements Runnable {
    @Autowired
    private Jobs4uUserAuthService authService;
    @Autowired
    private DeliverListOfJobOpenings deliverListOfJobOpenings;
    @Autowired
    private DeliverListOfNotifications deliverListOfNotifications;
    @Autowired
    private CandidateRepository candidateRepository;
    private Socket socket;
    private DataOutputStream sOut;
    private DataInputStream sIn;
    @Autowired
    private DeliverListOfJobApplications deliverListOfJobApplications;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InetAddress clientIP;

        clientIP = socket.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + socket.getPort());

        try {
            sOut = new DataOutputStream(socket.getOutputStream());
            sIn = new DataInputStream(socket.getInputStream());
            MessageFormat format;
            do {
                format = MessageReader.readMessage(sIn);
                assert format != null;
                switch (Objects.requireNonNull(format.getCode())) {
                    case AUTH:
                        try {
                            String role = doLogin(format);
                            sOut.write(new MessageFormat((byte) 0, MessageCode.ACK, List.of(role, authService.authenticatedUser().getEmail().toString())).toBytes());
                        } catch (Exception e) {
                            sOut.write(new MessageFormat((byte) 0, MessageCode.ERR, List.of("null", "null")).toBytes());
                        }
                        break;
                    case GETJOBOP:
                        List<JobOpeningSmallDTO> jobOpeningSmallDTOS = deliverListOfJobOpenings.createListOfDTO(new String(format.getDatas().get(0).getData()));
                        List<MessageData> datasOpenings = new ArrayList<>();
                        for (JobOpeningSmallDTO dto : jobOpeningSmallDTOS) datasOpenings.add(new MessageData(dto));
                        sOut.write(new MessageFormat((byte) 0, datasOpenings, MessageCode.JOBOPLST).toBytes());
                        break;
                    case GETNOTIFS:
                        List<NotificationDTO> notificationDTOS = deliverListOfNotifications.getListOfDTO(new String(format.getDatas().get(0).getData()), Longs.fromByteArray(format.getDatas().get(1).getData()));
                        List<MessageData> datasNotif = new ArrayList<>();
                        for (NotificationDTO dto : notificationDTOS) datasNotif.add(new MessageData(dto));
                        sOut.write(new MessageFormat((byte) 0, datasNotif, MessageCode.NOTIFLST).toBytes());
                        break;
                    case READNOTIF:
                        try {
                            deliverListOfNotifications.markNotificationAsRead(Longs.fromByteArray(format.getDatas().get(0).getData()));
                            sOut.write(new MessageFormat((byte) 0, MessageCode.ACK, List.of()).toBytes());
                        } catch (Exception e) {
                            sOut.write(new MessageFormat((byte) 0, MessageCode.ERR, List.of()).toBytes());
                        }
                        break;
                    case GETJOBAP:
                        EmailAddress email = authService.authenticatedUser().getEmail();
                        Candidate candidate = candidateRepository.findCandidateByEmailAddress(email);
                        List<JobApplicationSmallDTO> jobApplicationSmallDTOS = deliverListOfJobApplications.createListOfDTO(candidate);
                        List<MessageData> datasApplications = new ArrayList<>();
                        for (JobApplicationSmallDTO dto : jobApplicationSmallDTOS)
                            datasApplications.add(new MessageData(dto));
                        sOut.write(new MessageFormat((byte) 0, datasApplications, MessageCode.JOBAPLST).toBytes());
                        break;
                }
            }
            while (!format.getCode().equals(MessageCode.DISCONN));
            sOut.write(new MessageFormat((byte) 0, MessageCode.ACK, List.of("")).toBytes());
            System.out.println("Client " + clientIP.getHostAddress() + ", port number: " + socket.getPort() +
                    " disconnected");
            socket.close();
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }

    private String doLogin(MessageFormat format) {
        String username = new String(format.getDatas().get(0).getData()),
                password = new String(format.getDatas().get(1).getData());
        if (authService.authenticate(username, password)) return authService.authenticatedUser().getRole().toString();
        else return "null";
    }
}
