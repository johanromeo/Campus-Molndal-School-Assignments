import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<String> listOfNames = new ArrayList<>();
        String[] menu = {
                "+----------------------+",
                "| Den söta kattlistan  |",
                "+----------------------+",
                "|1) Mata in ett namn   |",
                "|2) Radera ett namn    |",
                "|3) Ändra ett namn     |",
                "|4) Lista alla namn    |",
                "+----------------------+"
        };

        int menuOption = 0;
        String inputName = "";
        boolean isDone = false;

        while (!isDone) {

            // Anropar meny samt uppdaterar lista. Hur ändra till snyggare?
            System.out.println("Listan innehåller " + listOfNames.size() + " namn");
            for (String choise : menu) {
                System.out.println(choise);
            }

            // Lägger till namn i listan
            menuOption = input.nextInt();
            if (menuOption == 1) {
                System.out.println("Mata in ett namn");
                inputName = input.next();
                listOfNames.add(inputName);
            }

            // Loopar igenom lista, kollar om namn finns och tar bort i så fall
            if (menuOption == 2) {
                System.out.println("Vilket namn vill du ta bort?");
                inputName = input.next();
                boolean nameExists = listOfNames.contains(inputName);
                for (int i = 0; i < listOfNames.size(); i++) {
                    if(nameExists) {
                        listOfNames.remove(i);
                    } else {
                        System.out.println(inputName + " finns inte i listan.");
                    }
                }
            }
            // Loopar igenom lista, frågar användare om namnbyte
            if (menuOption == 3) {

                String oldName = "";
                System.out.println("Vilket namn vill du ändra?");
                oldName = input.next();
                boolean nameExists = listOfNames.contains(oldName);

                for (int i = 0; i < listOfNames.size(); i++) {
                    if(nameExists) {
                        System.out.println("Ange nytt namn: ");
                        listOfNames.set(i, inputName = input.next());
                        System.out.println(oldName + " byttes till " + inputName);
                    } else {
                        System.out.println(inputName + " finns inte i listan.");
                    }
                }
            }
            // Skriver ut alla namn i listan
            if (menuOption == 4) {
                System.out.print("Namn i listan: ");

                for (String name : listOfNames) {
                    System.out.print(name + ", \n\n\n");
                }
            }
            // Avslutar program
            if (menuOption == 5)
                isDone = true;
        }
        System.out.println("Program avslutas. . . ");
    }
}


