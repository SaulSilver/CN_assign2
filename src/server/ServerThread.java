package server;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;

import java.io.File;
import java.io.IOException;

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

            htmlContent = htmlReader("C:\\Users\\zenbook\\Dropbox\\My Files\\Linnaeus University\\4. Fourth Semester\\Computer Networks - an introduction\\Assignment 2\\CN_assign2\\images\\index.html");

           // String htmlFileContents = htmlFile(filePath);
            FileInputStream fileName = new FileInputStream(filePath);
            output.writeBytes(htmlContent);           //Write the html file to the browser
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

    /**
     * A method to read an html file
     * @param filePath
     * @return
     */
    private String htmlFile(String filePath){
        String sb = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("F:\\Computer Networks\\Assignment 2\\images\\index.html")); //to read from html file
            String str = in.readLine();
            while (str != null) {
                sb += str;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    private String htmlReader (String filePath){

        try {
        File myHTML = new File (filePath);
        Scanner reader = new Scanner(myHTML); // create scanner to 'read' text
        String returnThis = "";

        while (reader.hasNextLine()){
            returnThis = returnThis + reader.nextLine() + "\n"; //attach all lines of text to String
        }

            return returnThis;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR MESSAGE";
    }
}