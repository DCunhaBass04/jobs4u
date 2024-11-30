package jobs4u.app.candidate.console;

import jobs4u.app.candidate.console.messages.MessageCode;
import jobs4u.app.candidate.console.messages.MessageFormat;
import jobs4u.app.candidate.console.messages.MessageReader;
import jobs4u.app.candidate.console.menu.CandidateMenu;
import jobs4u.app.candidate.console.menu.LoginMenu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;

public class CandidateApp {
    static InetAddress serverIP;
    static Socket sock;
    public static void main(String[] args) throws IOException {
        System.out.println("---------------Candidate App---------------");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("jobs4u.app.candidate.console/src/main/resources/application.properties"));
        } catch (IOException e) {
            System.out.println("\"application.properties\" file was not found. Exiting");
            System.exit(1);
        }
        try { serverIP = InetAddress.getByName(properties.getProperty("server.address")); }
        catch(UnknownHostException ex) {
            System.out.println("Invalid server specified: " + properties.getProperty("server.address"));
            System.exit(1); }

        try { sock = new Socket(serverIP, Integer.parseInt(properties.getProperty("server.port"))); }
        catch(IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1); }

        System.out.println("Established conection to server");
        LoginMenu loginMenu = new LoginMenu();
        String email = loginMenu.show(sock);
        System.out.println();
        if(email != null){
            CandidateMenu mainMenu = new CandidateMenu();
            mainMenu.doShow(sock, email);
        } else {
            System.out.println("Login failed, exiting");
        }
        closeConnection();
    }
    private static void closeConnection() throws IOException {
        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
        DataInputStream sIn = new DataInputStream(sock.getInputStream());
        sOut.write(new MessageFormat((byte) 0, MessageCode.DISCONN, List.of("")).toBytes());
        MessageFormat format = MessageReader.readMessage(sIn);
        sock.close();
    }
}