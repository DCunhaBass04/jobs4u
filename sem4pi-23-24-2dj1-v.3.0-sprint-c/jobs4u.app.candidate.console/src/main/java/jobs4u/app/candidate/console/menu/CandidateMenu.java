package jobs4u.app.candidate.console.menu;

import jobs4u.app.candidate.console.presentation.ListJobApplications;

import java.io.IOException;
import java.net.Socket;

public class CandidateMenu {
    private Socket sock;
    private String email;
    private Menu mainMenu = new Menu();
    public void doShow(Socket socket, String email){
        sock = socket;
        this.email = email;
        mainMenu = new Menu();
        buildMenu();
        mainMenu.run();
    }
    private void buildMenu(){
        try {
            mainMenu.addOption(new ListJobApplications(sock, email));
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Terminating!");
        }
    }
}