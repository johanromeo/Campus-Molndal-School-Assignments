package main.app;

import main.fileManager.FileManager;
import main.ftp.FTPHandler;
import main.input.UserInput;
import main.output.Output;
import org.apache.commons.net.ftp.FTPClient;

import java.util.Scanner;

public class Application {

    private FTPHandler ftpHandler;
    private UserInput input;
    private FileManager fileManager;

    public Application() {
        ftpHandler = new FTPHandler(new FTPClient());
        input = new UserInput(new Scanner(System.in));
        fileManager = new FileManager();
    }

    public void run() {
        boolean isDone = false;

        while (!isDone) {
            Output.showMenu();
            switch (input.getUserIntInput()) {
                case 1 -> userCreatingFile();
                case 2 -> userReadingDirectoryFiles();
                case 3 -> userAddingFile();
                case 4 -> userReadingFTPFiles();
                case 5 -> isDone = true;
            }
        }
    }

    private void userCreatingFile() {
        boolean isDone = false;
        Output.promptUserToWriteContent();
        while (!isDone) {
            String content = input.getUserStringInput();
            Output.promptUserToGiveFileName();
            String fileName = input.getUserStringInput();
            fileManager.createFile(content, fileName);
            isDone = true;
        }
    }

    private void userAddingFile() {
        boolean isDone = false;
        Output.askUserWichFileToAdd();
        while (!isDone) {
            ftpHandler.addFileToFTPServer(input.getUserStringInput());
            isDone = true;
        }
    }
    private void userReadingFTPFiles() {
        boolean isDone = false;
        while (!isDone) {
            ftpHandler.readFilesFromFTPServer();
            Output.askUserWichFileToRead();
            ftpHandler.readFileFromFTPServer(input.getUserStringInput());
            isDone = true;
        }
    }

    private void userReadingDirectoryFiles() {
        boolean isDone = false;
        while (!isDone) {
            Output.listAllFilesInDirectory(fileManager);
            isDone = true;
        }
    }
}
