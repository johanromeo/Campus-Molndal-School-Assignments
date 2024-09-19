package main.ftp;

import main.output.Output;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
public class FTPHandler {
    //Set hostName, userName and userPassword to your personal login credentials
    private final String hostName = "127.0.0.1";
    private final String userName = "SET_YOUR_USER_NAME_HERE";
    private final String userPassword = "SET_YOUR_PASSWORD_HERE";
    private final String workingDirectory = "/upload/";
    // Set the same path as in the FileManager class
    private final String pathName = "SET\\YOUR\\OWN\\PATH\\HERE";
    private FTPClient ftpClient;

    public FTPHandler (FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    private void connectToFTPServer() {
        try {
            ftpClient.connect(hostName);
            ftpClient.login(userName, userPassword);
            ftpClient.changeWorkingDirectory(workingDirectory);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFileToFTPServer(String fileToAdd) {
        connectToFTPServer();

        try {
            File file = new File(pathName + fileToAdd);
            FileInputStream inputStream = new FileInputStream(file);
            ftpClient.storeFile(fileToAdd, inputStream);
            inputStream.close();
            closeConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFileFromFTPServer(String fileToRead) {
        connectToFTPServer();

        try {
            File file = new File(fileToRead);
            ftpClient.retrieveFile(fileToRead, new FileOutputStream(file));
            BufferedReader reader = new BufferedReader(new FileReader(fileToRead));
            String output;

            while((output = reader.readLine()) != null) {
                Output.printFileToRead(output);
            }
            closeConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFilesFromFTPServer() {
        connectToFTPServer();
        FTPFile[] files;
        try {
            files = ftpClient.listFiles();
            Output.printFilesToRead(files);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void closeConnection() throws IOException {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
