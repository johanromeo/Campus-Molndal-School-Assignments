package main.output;

import main.fileManager.FileManager;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;

public class Output {
    /**Menu options shown to user*/
    public static void showMenu() {
        System.out.println("""
                1. Create file in a directory
                2. Read files from a directory
                3. Add file to your local FTP server
                4. Read files from your local FTP server
                5. Exit
                """);
    }

    public static void promptUserToWriteContent() {
        System.out.println("Give your file a content: ");
    }

    public static void promptUserToGiveFileName() {
        System.out.println("""
                Give your file a name and specify its type.
                Example: "myFile.txt"
                """);
    }
    public static void askUserWichFileToAdd() {
        System.out.println("""
                Type the exact name of the file you want to add
                Example: "myFile.txt"
                """);
    }
    public static void askUserWichFileToRead() {
        System.out.println("""
                Type the exact name of the file you want to read
                Example: "myFile.txt"
                """);
    }

    /**Output for the FTPHandler class*/
    public static void printFileToRead(String output) {
        System.out.println(output);
    }
    public static void printFilesToRead(FTPFile[] files) {
        System.out.println("List of all files on your local FTP server");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ") " + files[i].getName());
        }
        System.out.println("_______________________________________");
    }

    /**Output for the FileManager class*/

    public static void listAllFilesInDirectory(FileManager fileManager) {
        System.out.println("List of all files in your directory");
        File[] files = fileManager.getFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}
