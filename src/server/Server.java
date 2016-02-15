package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int MY_PORT = 8888;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(MY_PORT);           //Creating a socket for
        System.out.println("Started");
        Socket socket;

        while(true){
            socket = serverSocket.accept();                      //Creating the socket for the client
            new ServerThread(socket).start();              //Creating a new thread for the client
            System.out.println("Client is connected");
        }
    }
}
