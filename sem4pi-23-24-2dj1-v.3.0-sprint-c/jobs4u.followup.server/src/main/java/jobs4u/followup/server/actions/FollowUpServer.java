package jobs4u.followup.server.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class FollowUpServer {
    private ServerSocket socket;
    @Autowired
    private FollowUpServerThread thread;
    public void run() throws IOException {
        Socket cliSock;
        try{socket = new ServerSocket(1979);} catch (IOException e) {
            System.out.println("Oops");
            System.exit(1);
        }
        System.out.println("Connected!");
        System.out.println("Waiting for clients...");
        while(true) {
            cliSock=socket.accept();
            thread.setSocket(cliSock);
            new Thread(thread).start();
        }
    }
}
