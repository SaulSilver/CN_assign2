package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Hatem on 2/25/2016.
 */
public class FolderHandler {

    private final String HTML_START = "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n<meta charset=\"UTF-8\">\n" +
            "<title>Files and folders names</title>" + "</head>\n<body>\n";
    private final String HTML_END = "</body>\n</html>";

    public String getFolders(String folderPath) {
        String htmlFilePath = "files\\htmlFiles\\fileNames.html";
        File directory = new File(folderPath);
        File[] foldersNames = directory.listFiles();                //an array of files in that directory

        FileWriter fWriter;
        BufferedWriter writer;
        try {
            fWriter = new FileWriter(htmlFilePath);
            writer = new BufferedWriter(fWriter);
            writer.write(HTML_START);
            if (foldersNames != null) {
                for(File f : foldersNames) {
                    writer.write("<p>" + "<a href =\"" + f.getAbsolutePath() + "\">" + f.getName() + "</a>"+"</p>");
                    writer.newLine();
                }
            }
            writer.write(HTML_END);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return htmlFilePath;
    }
}
