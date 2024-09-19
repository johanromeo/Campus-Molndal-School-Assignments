package client;

import java.util.Scanner;

/**
 * Utility class for user input handling, providing methods for reading strings and integers from the console.
 */
public class Input {

    private final Scanner userInput;


    public Input() {
        this.userInput = new Scanner(System.in);
    }

    public String stringInput(String prompt) {
        String stringInput;
        Output.printPrompt(prompt);

        while (true) {
            stringInput = userInput.nextLine();
            // regex is used here to check for unwanted characters, which are not supported by the Apache HTTP Client 5
            boolean isCorrect = !stringInput.isEmpty() && !stringInput.isBlank() && !stringInput.matches(".*[åäöÅÄÖ].*");

            if (isCorrect)
                return stringInput.trim();
            else
                Output.printError("Input mismatch! Try again, fool -> ");
        }
    }
    public int integerInput(int menuIndexStart, int menuIndexEnd) {
        int userChoice;

        while (true) {

            try {
                // .nextLine() is used here instead of .nextInt() because of unwanted code jumps
                userChoice = Integer.parseInt(userInput.nextLine());
                if ( (userChoice < menuIndexStart) || (userChoice > menuIndexEnd) ) {
                    Output.printError("Not a valid menu choice, try again - > ");
                }
                else return userChoice;
            } catch (NumberFormatException e ) {
                Output.printError("A String is not a valid input, try again - > ");
            }
        }
    }
}

