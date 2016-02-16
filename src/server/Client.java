package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Hatem on 2/16/2016.
 */
public class Client {
    public static void main(String[] args) {

        char[] buf = new char[1024];
        Socket socket;
        BufferedReader input;
        OutputStreamWriter output;

        try {
            socket = new Socket("194.47.123.88", 8888);


		/* Create stream for sending message */
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		/* Create stream for receiving echoed message */
            output = new OutputStreamWriter(socket.getOutputStream());

			/* Send message*/
                output.write("Test");
                output.flush();

            /* receive message */
                String read = input.readLine();
                System.out.println(read);
              //  readMsg.setLength(0);                               //Emptying the StringBuffer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

