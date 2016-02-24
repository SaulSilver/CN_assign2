package server;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Hatem on 2/23/2016.
 */
public class HTTPResponse {
    int httpCode;       //e.g. 200 OK or 404 Not found
    String response;        //The whole response header

    public HTTPResponse(){
        response = "HTTP-Version: HTTP/1.0 ";
    }

    public void formHTTPResponse(int returnCode, String filePath, char fileType) {
        switch (returnCode) {
            case 200:
                response += "200 OK\n";
                getContentLength(filePath);
                getContentType(fileType);
                break;
            case 403:
                response += "403 Forbidden\n";
                break;
            case 404:
                response += "404 File Not Found\n";
                break;
            case 500:
                response += "500 Internal Server Error\n";
                break;
        }
    }

    private void getContentLength(String filePath){
        File file = new File(filePath);
        response += "Content-Length: " + file.length() + "\n";
    }

    private void getContentType(char fileType){
        if(fileType =='h')
            response += "Content-Type: text/html";
        else if(fileType == 'p')
            response += "Content-Type: image/png";
        else if (fileType == 'i')
            response += "Content-Type: image/x-icon";
    }

    public String getResponse() {
        return response;
    }
}