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
    private DataOutputStream  output;

    public ServerThread(Socket clientSocket) {
        socket = clientSocket;
    }

    public void run() {
        BufferedReader input;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));         //Receiving from client
            String read = input.readLine();
            String filePath = httpGetFilePath(read);
            htmlReader(filePath);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to read the URL from the client "browser". Also specifies the type of the required file.
     * @param read
     * @return The file path
     */
    private String httpGetFilePath(String read) throws Exception{

        String tmp = read.substring(read.lastIndexOf(":") + 1, read.lastIndexOf(" "));
        System.out.println(tmp);
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
        filePath = filePath.replace('/', File.separator.charAt(0));
        System.out.println(filePath);
        if(!filePath.endsWith("html") && !filePath.endsWith("htm") && !filePath.endsWith("png"))
            throw new Exception("File type is not allowed.");

//        if(filePath.endsWith(".html") || filePath.endsWith(".htm"))
//            fileType = false;
//        else if (filePath.endsWith(".png"))
//            fileType = true;
        return filePath;
    }


    private void htmlReader (String filePath){
        try {
            File myHTML = new File (filePath);
            FileInputStream reader = new FileInputStream(myHTML); // create scanner to 'read' text
            output = new DataOutputStream(socket.getOutputStream());
            byte[] buf = new byte[1024];
            int count;
            while ((count = reader.read(buf)) >= 0)
                output.write(buf, 0, count);
            output.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}