package main.input;

import java.util.Scanner;

public class UserInput {
    private Scanner input;

    public UserInput(Scanner input) {
        this.input = input;
    }

    public int getUserIntInput() {
        int userInput = 0;

        while (true) {
            try {
                userInput = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return userInput;
    }
    public String getUserStringInput() {
        return input.nextLine();
    }
}
