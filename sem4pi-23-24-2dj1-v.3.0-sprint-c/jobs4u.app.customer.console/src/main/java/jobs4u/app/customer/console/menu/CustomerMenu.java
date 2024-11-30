package jobs4u.app.customer.console.menu;

import jobs4u.app.customer.console.presentation.ListJobOpenings;
import jobs4u.app.customer.console.presentation.ListNotifications;

import java.io.IOException;
import java.net.Socket;

public class CustomerMenu {
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
            mainMenu.addOption(new ListJobOpenings(sock, email));
            mainMenu.addOption(new ListNotifications(sock, email));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Terminating");
        }
    }
}
