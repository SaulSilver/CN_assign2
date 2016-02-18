package server;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Hatem on 2/15/2016.
 */
public class ServerThread extends Thread {

    private Socket socket;
    private boolean fileType;

    public ServerThread(Socket clientSocket) {
        socket = clientSocket;
    }

    public void run() {
        BufferedReader input;
        DataOutputStream  output;
        String htmlContent = "";

        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));         //Receiving from client
            output = new DataOutputStream(socket.getOutputStream());      //Sending to Client

            String read = input.readLine();

            String filePath = httpGetFilePath(read);

            htmlContent = htmlReader("images\\index.html");

           // String htmlFileContents = htmlFile(filePath);
           // FileInputStream fileName = new FileInputStream(filePath);
            output.writeBytes(htmlContent);           //Write the html file to the browser
            System.out.println(htmlContent);
            output.writeBytes("");

            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String httpGetFilePath(String read) {
        String tmp = read.substring(read.lastIndexOf(":") + 1);
        //  System.out.println(tmp);
        String filePath = null;

        for(int i = 0; i < tmp.length(); i++){
            char a = tmp.charAt(i);
            if( a == '/'){
                if(tmp.endsWith("/"))
                    filePath = tmp.substring(tmp.indexOf(a)+1, tmp.length()-1);
                else filePath = tmp.substring(tmp.indexOf(a)+1);
                break;
            }
        }
        //  System.out.println(filePath);
        if(filePath.endsWith(".html") || filePath.endsWith(".htm"))
            fileType = false;
        else if (filePath.endsWith(".png"))
            fileType = true;
        return filePath;
    }

    private String htmlReader (String filePath){

        try {
        File myHTML = new File (filePath);
        Scanner reader = new Scanner(myHTML); // create scanner to 'read' text
        String returnThis = "";

        while (reader.hasNextLine()){
            returnThis = returnThis + reader.nextLine() + "\n"; //attach all lines of text to String
        }
            reader.close();
            return returnThis;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR MESSAGE";
    }
}