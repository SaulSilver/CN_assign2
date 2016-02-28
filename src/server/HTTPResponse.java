package server;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * A class to create the HTTP response to the browser
 * Created by Hatem on 2/23/2016.
 */
public class HTTPResponse {
    String response;        //The whole response header

    public HTTPResponse(){
        response = "HTTP-Version: HTTP/1.1 ";
    }

    public void formHTTPResponse(int returnCode, File file, char fileType) {
        switch (returnCode) {
            case 200:
                response += "200 OK" + "\r\n";
                getLastModified(file);
                getContentLength(file);
                getContentType(fileType);
                break;
            case 403:
                response += "403 Forbidden" + "\r\n";
                break;
            case 404:
                response += "404 File Not" + "\r\n";
                break;
            case 500:
                response += "500 Internal Server Error" + "\r\n";
                break;
        }
        getServerId();
    }
    private void getLastModified(File file) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        response += "Last-Modified: "+ sdf.format(file.lastModified()) + "\r\n";
    }

    private void getContentLength(File file){
        response += "Content-Length: " + file.length() + "\r\n";
    }

    private void getContentType(char fileType){
        if(fileType =='h')

            response += "Content-Type: text/html" + "\r\n";
        else if(fileType == 'p')
            response += "Content-Type: image/png" + "\r\n";
        else if (fileType == 'i')

            response += "Content-Type: image/x-icon" + "\r\n";
    }

    private void getServerId(){
        response += "Server: Hatem & Mauro simple server" + "\r\n";
    }

    public String getResponse() {

        return response + "\r\n";
    }
}