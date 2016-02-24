package server;

import java.io.*;
import java.net.Socket;

/**
 * A class to handle http requests
 * Created by Hatem on 2/23/2016.
 */
public class HTTPHandler {

    public HTTPHandler(){

    }

    public void getRequest(String url, Socket socket){
        String filePath = httpGetFilePath(url);         //Extract the file path from the whole URL

        FileHandler file = new FileHandler();
        file.checkFileType(filePath);           //checks if the file is html, htm, png or ico file
        File requestedFile = file.checkValidity(filePath);      //Checks the file status; 200 OK, 404, 500 or 403.
        file.readFile(socket, requestedFile);       //Sends the response to the browser
    }

    /**
     * A method to read the URL from the client "browser". Also specifies the type of the required file.
     * @param url: URL request
     * @return The file path
     */
    private String httpGetFilePath(String url){

        String tmp = url.substring(url.lastIndexOf(":") + 1, url.lastIndexOf(" "));
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
        if (filePath != null)
            filePath = filePath.replace('/', File.separator.charAt(0));
        System.out.println(filePath);

        return filePath;
    }

}
