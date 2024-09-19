import java.util.Scanner;

public class Menu {
    // Shows the main menu
    public static void showMainMenu() {
        System.out.println("1. Play a Lord of the Rings Quote Game, you fool!");
        System.out.println("2. Remove your local database and all its collections and documents");
        System.out.println("3. Throw yourself into the pits of Mount Doom and exit game!");
    }
    // Static method that gets the user's input. Used where there is need for user input.
    public static int getUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Make your choice, Frodo: ");
        int choice = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            if (input.hasNextInt()) {
                choice = input.nextInt();
                isValidInput = true;
            } else {
                System.out.println("May you be thrown into the pits of Mount Doom! I demand a number!");
                input.nextLine();
            }
        }
        return choice;
    }
}
