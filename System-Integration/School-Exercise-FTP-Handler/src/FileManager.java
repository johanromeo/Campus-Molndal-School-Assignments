package main.fileManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    // Set the pathName to the path of your chosen directory
    private final String pathName = "SET\\YOUR\\OWN\\PATH\\HERE";
    public void createFile(String content, String fileName) {

        try {
            FileWriter fileWriter = new FileWriter(pathName + fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File[] getFiles() {
        File filesInMap = new File(pathName);
        return filesInMap.listFiles();
    }
}
