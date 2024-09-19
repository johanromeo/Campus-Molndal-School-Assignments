import java.util.ArrayList;
import java.util.Scanner;

public class WordList {
    private ArrayList<String> hogwartsNames;
    public WordList() {
        hogwartsNames = new ArrayList<>();
        hogwartsNames.add("hermione");
        hogwartsNames.add("harry");
        hogwartsNames.add("ron");
        hogwartsNames.add("voldermort");
        hogwartsNames.add("albus");
        hogwartsNames.add("dumbledore");
        hogwartsNames.add("snape");
        hogwartsNames.add("bellatrix");
    }
    public String generateWord() {
        int randomIndex = (int) (Math.random() * hogwartsNames.size());
        return hogwartsNames.get(randomIndex);
    }
    public void printList() {
        System.out.println("List of all the Hogwarts names: ");
        for (String hogwartsName : hogwartsNames) {
            System.out.print(hogwartsName + ", ");
        }
    }
    public void addNameToList() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a new Hogwarts name: ");
        hogwartsNames.add(input.nextLine().toLowerCase());
    }

    public void removeNameFromList() {
        Scanner input = new Scanner(System.in);
        System.out.println("List of all the Hogwarts names: ");
        for (int i = 0; i < hogwartsNames.size(); i++) {
            System.out.println((i + 1) + ": " + hogwartsNames.get(i));
        }

        System.out.print("What poor bastard you want removed? ");
        int indexToRemove = input.nextInt() - 1;

        if (indexToRemove >= 0 && indexToRemove < hogwartsNames.size()) {
            String removedName = hogwartsNames.remove(indexToRemove);
            System.out.println(removedName + " is now thrown in the Forbidden Forest... OH NO!");
        } else {
            System.out.println("Wrong number, fool! Next time i break your wand!");
        }
    }
}