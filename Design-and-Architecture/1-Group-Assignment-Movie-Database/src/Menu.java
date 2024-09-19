import java.util.Scanner;

public class Menu {


    public void showMainMenu() {    // Visar Main menyn J.R
        System.out.println("+-------------------------------+");
        System.out.println("|     1. SHOW ALL MENU          |");
        System.out.println("|     2. SEARCH MENU            |");
        System.out.println("|     3. SHOW INFORMATION MENU  |");
        System.out.println("+-------------------------------+");
    }

    public void showAll() {     // Visar show ALL menyn J.R
        System.out.println("+-------------------------------+");
        System.out.println("|     1. Show all movies        |");
        System.out.println("|     2. Show all actors        |");
        System.out.println("|     3. Show all directors     |");
        System.out.println("|     4. Show all genres        |");
        System.out.println("|     5. Show all years         |");
        System.out.println("+-------------------------------+");


        }

        public void searchBy () { // Visar search by menyn J.R
            System.out.println("+-------------------------------+");
            System.out.println("|     1. Search by movie        |");
            System.out.println("|     2. Search by actor        |");
            System.out.println("|     3. Search by director     |");
            System.out.println("|     4. Search by genre        |");
            System.out.println("|     5. Search by year         |");
            System.out.println("+-------------------------------+");
        }
        public void showInformation () { // Visar show information menyn J.R
            System.out.println("+----------------------------------------+");
            System.out.println("|     1. Information about a movie       |");
            System.out.println("|     2. Information about an actor      |");
            System.out.println("|     3. Information about a director    |");
            System.out.println("|     4. Information about a genre       |");
            System.out.println("|     5. Information about a year        |");
            System.out.println("+----------------------------------------+");
        }
        public int userChoice () { // Tar in användarens val J.R
            Scanner input = new Scanner(System.in);
            int userChoice = 0;
            try {
                System.out.print("Make a choice: ");
                userChoice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input");
            }
            return userChoice;
        }
    }


