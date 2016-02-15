package server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Hatem on 2/15/2016.
 */
public class ServerThread extends Thread {

    public final int BUFFER_SIZE = 1024;
    private Socket socket;

    public ServerThread(Socket clientSocket){
        socket = clientSocket;
    }

    public void run(){
        char[] buf= new char[BUFFER_SIZE];
        InputStreamReader input;
        OutputStreamWriter output;

        try {
            input = new InputStreamReader(socket.getInputStream());         //Receiving from client
            output = new OutputStreamWriter(socket.getOutputStream());      //Sending to Client
            StringBuffer readMsg = new StringBuffer();

            while(true){
                int read = input.read(buf, 0, BUFFER_SIZE);
                if(read == -1)                         //If it is the end of the buffer
                    break;
                readMsg.append(buf, 0, read);
                if(readMsg == null) {
                    socket.close();
                    return;
                }
                output.write(String.valueOf(readMsg));
                output.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}