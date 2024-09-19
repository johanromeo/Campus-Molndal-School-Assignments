import java.util.Scanner;

public class Menu {
    public void showMenu() {
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("1. Take a sneaky peak at the names already in the list, you cheater!");
        System.out.println("2. Add a Hogwarts name to the list");
        System.out.println("3. Remove a Hogwarts name from the list");
        System.out.println("4. Start the Hangman Hogwarts Edition Game");
        System.out.println("5. Leave the gates of Hogwarts, never to return");
        System.out.println("-------------------------------------------------------------------------");
    }
    public int playerMenuChoise() {
        Scanner input = new Scanner(System.in);
        int menuOption = 0;
        try {
            System.out.println("Make a menu choice");
            menuOption = input.nextInt();
        } catch(Exception e) {
            System.out.println("Invalid choice");
        } return menuOption;
    }
}
