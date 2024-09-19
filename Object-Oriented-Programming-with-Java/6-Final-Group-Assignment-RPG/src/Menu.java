import java.util.Scanner;
public class Menu {
    public void showMainMenu() {
        System.out.println(" __________________________________________ ");
        System.out.println("                  Game Menu                 ");
        System.out.println(" ------------------------------------------ ");
        System.out.println("|   1. Start adventure                     |");
        System.out.println("|------------------------------------------|");
        System.out.println("|   2. Show Player Stats                   |");
        System.out.println("|------------------------------------------|");
        System.out.println("|   3. Visit Shop                          |");
        System.out.println("|__________________________________________|");
        System.out.println("|   4. Exit game                           |");
        System.out.println("|__________________________________________|");
    }
    public void choseOpponent() {
        System.out.println("1. Fight a useless Snake");
        System.out.println("2. Fight Voldemort");
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


