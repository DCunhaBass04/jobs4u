package jobs4u.app.customer.console.menu;

import eapli.framework.io.util.Console;
import jobs4u.app.customer.console.messages.MessageCode;
import jobs4u.app.customer.console.messages.MessageEncoder;
import jobs4u.app.customer.console.messages.MessageFormat;
import jobs4u.app.customer.console.messages.MessageReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class LoginMenu {
    private DataOutputStream sOut;
    private DataInputStream sIn;
    static Socket sock;
    private static final byte VERSION = 1;
    public String show(Socket socket) {
        System.out.println(headline());
        sock = socket;
        try {
            sOut = new DataOutputStream(sock.getOutputStream());
            sIn = new DataInputStream(sock.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        String rawUsername, rawPassword;
        int attempt = 0;
        boolean valid = false;
        do {
            rawUsername = Console.readLine("Enter your username: ");
            rawPassword = Console.readLine("Enter your password: ");
            attempt++;
            try {
                List<String> data = doLogin(rawUsername, rawPassword);
                if(data.get(0).equalsIgnoreCase("CUSTOMER"))
                    return data.get(1);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (!valid) {
                System.out.println("Incorrect credentials, try again (" + (3 - attempt) + " attempts left).");
            }
        } while (attempt != 3 && !valid);


        return null;
    }
    private List<String> doLogin(String rawUsername, String rawPassword) throws IOException {
        List<Byte> bytes = new ArrayList<>();
        bytes.add(VERSION);
        bytes.add(MessageCode.AUTH.getValue());

        MessageEncoder.addBytesFromString(bytes, rawUsername);
        MessageEncoder.addBytesFromString(bytes, rawPassword);
        bytes.add((byte)0); bytes.add((byte)0);

        sOut.write(MessageReader.getArrayOfBytes(bytes));
        MessageFormat format = MessageReader.readMessage(sIn);

        assert format != null;
        List<String> values = new ArrayList<>();
        values.add(new String(format.getDatas().get(0).getData()));
        values.add(new String(format.getDatas().get(1).getData()));
        return values;
    }
    public String headline() {
        return "Customer Login Menu";
    }
}
