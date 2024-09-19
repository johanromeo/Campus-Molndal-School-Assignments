import java.util.Scanner;

public class Menu {
    public static void showMenu() {
        System.out.println("+---------------------------+");
        System.out.println("1. Create Menu              |");
        System.out.println("2. Read Menu                |");
        System.out.println("3. Update Menu              |");
        System.out.println("4. Delete Menu              |");
        System.out.println("5. Exit program             |");
        System.out.println("+---------------------------+");
    }
    public static void showCreateMenu() {
        System.out.println("+------------------------------------------------+");
        System.out.println("1. Add a new Costumer to your Mongo Database     |");
        System.out.println("2. Add a new Employee to your Mongo Database     |");
        System.out.println("3. Go back to Main Menu                          |");
        System.out.println("+------------------------------------------------+");
    }
    public static void showReadMenu() {
        System.out.println("+------------------------------------------------+");
        System.out.println("1. Read all Costumers from your Mongo Database   |");
        System.out.println("2. Read all Employees from your Mongo Database   |");
        System.out.println("3. Read all from your Mongo Database             |");
        System.out.println("4. Go back to Main Menu                          |");
        System.out.println("+------------------------------------------------+");
    }
    public static void showUpdateMenu() {
        System.out.println("+------------------------------------------------+");
        System.out.println("1. Update a Costumer in your Mongo Database      |");
        System.out.println("2. Update an Employee in your Mongo Database     |");
        System.out.println("3. Go back to Main Menu                          |");
        System.out.println("+------------------------------------------------+");
    }
    public static void showDeleteMenu() {
        System.out.println("+------------------------------------------------+");
        System.out.println("1. Delete a Costumer from your Mongo Database    |");
        System.out.println("2. Delete an Employee from your Mongo Database   |");
        System.out.println("3. Go back to Main Menu                          |");
        System.out.println("+------------------------------------------------+");
    }
    public static int getUserChoise() {
        Scanner input = new Scanner(System.in);
        int choise = 0;
        try{
            System.out.print("Enter an option -> ");
            choise = input.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return choise;
    }
}
