import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Bytte från namn -> name
        String[] namn = {"Elena Gilbert",
                "Stefano Salvatore",
                "Damon Salvatore",
                "Caroline Forbes",
                "Bonnie Bennett",
                "Tyler Lockwood",
                "Matt Damon",
                "Alaric Saltzman",
                "Klaus Mikaelson",
                "Rebekah Mikaelson"};

        // Bytte från ålder -> year
        int[] ålder = {17, 162, 177, 17, 17, 22, 19, 41, 1000, 1012};

        System.out.println("\nPersoner under 20 år: ");
        // Frågade chatGPT eftersom jag fick fel när jag hade alla
        // if-satser under samma for-loop så jag delade upp dem istället. Tack.
        for (int i = 0; i < namn.length; i++) {
            if (ålder[i] < 20)
                System.out.println(namn[i] + " är " + ålder[i] + " år gammal");
        } System.out.println("_________________________");


        System.out.println("\nPersoner som är över 20(?) och under 30:");
        for (int i = 0; i < namn.length; i++) {
            if ((ålder[i] > 20) && (ålder[i] < 30))
                System.out.println(namn[i] + " är " + ålder[i] + " år gammal");
        } System.out.println("_________________________");

        System.out.println("\nPersoner som är yngre än 20 och äldre än 29: ");
        for (int i = 0; i < namn.length; i++) {
            if ((ålder[i] < 20) || (ålder[i] > 29))
                System.out.println(namn[i] + " är " + ålder[i] + " år gammal");
        } System.out.println("_________________________");

        System.out.println("\nVisa namn och ålder på äldsta och yngsta karaktären:");
        Arrays.sort(ålder); // Klurar fortfarande på detta. Hur kan man göra det snyggare?
        String youngestName = namn[0];
        String oldestName = namn[namn.length-1];
        int youngest = ålder[0];
        int eldest = ålder[ålder.length-1];

        System.out.println("Äldsta personen är: " + oldestName + " på " + eldest + " år");
        System.out.println("Yngsta personen är: " + youngestName + " på " + youngest + " år");
        System.out.println("_________________________");


        System.out.println("\nPersoner som har jämna tal i sin ålder: ");
        for (int i = 0; i < namn.length; i++) {
            if (ålder[i] % 2 == 1)
                continue;
            System.out.println(namn[i] + " är " + ålder[i] + " år gammal");
        } System.out.println("_________________________");

        System.out.println("\nVisa alla namn men stoppa vid första person över 40: ");
        for (int i = 0; i < namn.length; i++) {
            System.out.println(namn[i] + " är " + ålder[i] + " år gammal");
            // Jag tolkar det som att stanna men även visa Tyler Lockwood som är över 40 år (41 år gammal.)
            if (ålder[i] > 40)
                break;
        }

    }
}
