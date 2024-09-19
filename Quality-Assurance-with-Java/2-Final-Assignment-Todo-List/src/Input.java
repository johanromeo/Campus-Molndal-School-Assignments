package org.campusmolndal;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Input class handles user input operations.
 */
public class Input {
    private Scanner input;

    /**
     * Constructs an Input object with the specified scanner for user input.
     *
     * @param input the scanner object used for reading user input
     */
    public Input(Scanner input) {
        this.input = input;
    }

    /**
     * Reads a non-negative integer input from the user.
     *
     * @return the non-negative integer input from the user
     */
    public int readCorrectIntFromUser() {
        int answer = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                answer = input.nextInt();
                isValid = errorCheckIntInput(answer);
            } catch (InputMismatchException e) {
                input.next();
                Output.tellUserWrongInput();
            }
        }
        return answer;
    }

    private boolean errorCheckIntInput(int answer) {
        if (answer < 0) {
            Output.promptUserForCorrectInt();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Reads a non-empty string input from the user.
     *
     * @return the non-empty string input from the user
     */
    public String readStringFromUser() {
        String answer = "";
        boolean isValid = false;

        while (!isValid) {
            try {
                answer = input.nextLine();
                isValid = errorChecksStringInput(answer);
            } catch (Exception e) {
                Output.tellUserEmptyString();
            }
        }

        return answer;
    }

    private boolean errorChecksStringInput(String input) {
        return !input.trim().isEmpty();
    }

    /**
     * Consumes the newline character from the input stream.
     */
    public void consumeNewLine() {
        input.nextLine();
    }
}
