package server;

import java.io.File;

/**
 * Created by Hatem on 2/23/2016.
 */

public class HTTPResponse {
    String response;        //The whole response header

    public HTTPResponse(){
        response = "HTTP-Version: HTTP/1.0 ";
    }

    public void formHTTPResponse(int returnCode, File file, char fileType) {
        switch (returnCode) {
            case 200:
                response += "200 OK\n";
                getContentLength(file);
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

    private void getContentLength(File file){
        response += "Content-Length: " + file.length() + "\n";
    }

    private void getContentType(char fileType){
        if(fileType =='h')
            response += "Content-Type: text/html\n";
        else if(fileType == 'p')
            response += "Content-Type: image/png\n";
        else if (fileType == 'i')
            response += "Content-Type: image/x-icon\n";
    }

    public String getResponse() {
        return response;
    }
}
