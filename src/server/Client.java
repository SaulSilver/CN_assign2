package server;

import java.io.*;
import java.net.*;

/**
 * Created by Hatem on 2/10/2016.
 *
 * A simple TCP client class.
 */
public class Client{
    public static final String MSG = "http://localhost:8888/src/images/index.html/";

    public static void main(String[] args) {
        int bufferSize = 1024;
        System.out.println("Client started");

        char[] buf = new char[bufferSize];

        Socket socket;
        BufferedReader input;
        DataOutputStream  output;

        try {
            socket = new Socket("192.168.1.3", 8888);

		/* Create stream for sending message */
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		/* Create stream for receiving echoed message */
            output = new DataOutputStream(socket.getOutputStream());

			/* Send message*/
            output.writeBytes(MSG);
            output.flush();
            /* receive message */
            //String in = input.readLine();

        output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}