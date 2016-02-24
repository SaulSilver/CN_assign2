package server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Hatem on 2/15/2016.
 */
public class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket clientSocket) {
        socket = clientSocket;
    }

    public void run() {
        BufferedReader input;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));         //Receiving from client
            String read = input.readLine();
            HTTPHandler httpHandler = new HTTPHandler();
            httpHandler.getRequest(read, socket);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}