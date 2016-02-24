package server;

import java.io.*;
import java.net.Socket;

/**
 * A class to handle the file requested in the URL
 * Created by Hatem on 2/23/2016.
 */
public class FileHandler {

    private char fileType;
    private int httpCode;
    private HTTPResponse httpResponse;
    private final int OK = 200;
    private final int NOT_FOUND = 404;
    private final int FORBIDDEN = 403;
    private final int INTERNAL_ERROR = 500;


    public char checkFileType(String filePath) {
        if (filePath.endsWith(".html") || filePath.endsWith(".htm"))
            fileType = 'h';
        else if (filePath.endsWith(".png"))
            fileType = 'p';
        else if (filePath.endsWith(".ico"))         //for favicon.ico files that are automatically generated by some browsers e.g. Google Chrome
            fileType = 'i';
        else {
            httpCode = FORBIDDEN;                     //code 403 Forbidden
        }
        return fileType;
    }

    public File checkValidity(String filePath){
        httpResponse = new HTTPResponse();
        File file = new File(filePath);
        if(!file.exists() && httpCode != FORBIDDEN) {
            httpCode = NOT_FOUND;
            file = new File("src\\404FileNotFound.html");
        }
        else if(httpCode == FORBIDDEN)
            file = new File("src\\403Forbidden.html");
        else{
            httpCode = OK;
        }
        return file;
    }

    public void readFile(Socket socket, File file){
        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            FileInputStream reader = new FileInputStream(file);       // create a reader for the required file

            byte[] buf = new byte[1024];
            int count;                                      //Counter to know the end of the file

            System.out.println(httpResponse.getResponse());              //Write the http response header

            while ((count = reader.read(buf)) >= 0)                     //Write the http response body
                output.write(buf, 0, count);

            output.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
