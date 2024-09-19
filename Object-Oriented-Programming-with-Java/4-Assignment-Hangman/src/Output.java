import java.util.ArrayList;
import java.util.Scanner;

public class Output {
    public void greetPlayer() {
        System.out.println("Welcome to Hangman - Hogwarts Edition\n");
        System.out.print("Please enter your name: ");
    }
    public void informPlayer(Player player) {
        System.out.println("Wohoo, " + player.getName() + "! Let's play!\n");
        System.out.println("You are given a random Hogwarts name and you will guess a letter. . .");
        System.out.println("You have a fixed number of guesses for each name!");
        System.out.println("May the Elder Wand be with you, " + player.getName());
    }
    public void tellPlayerNumberOfLetters(Word word, Player player) {
        System.out.println("\nYou have a word containing " + word.getWord().length() + " letters!");
        System.out.println("You have a total of " + player.getGuesses() + " guesses");
        System.out.println("If you guess correctly, you will not lose guesses, but if you fail to guess correctly you will get fewer guesses");
        System.out.println(word.getGuess());
        System.out.println("Let the guessing game begin!");
    }
    public void tellPlayerGuesses(Player player) {
        System.out.println("Hurray! You had " + player.getGuesses() + " guesses left. Bye!");
    }
    public void guessesLeft(Player player) {
        System.out.println("You have " + player.getGuesses() + " guesses left!");
    }

    public void tellPlayerHeWon(Player player, Word word) {
        System.out.println("Hurray, " + player.getName() + "! You made it. The word you guessed at was " + word.getWord() );
    }

    public void tellPlayerHeLost(Player player, Word word) {
        System.out.println("AHHHH... Voldermort cast Avada Kadavra on you, " + player.getName());
        System.out.println("Your eyes darken... The word you was " + word.getWord());
    }
    public void askPlayerForRematch() {
        System.out.println("Do you want a rematch? Enter \"yes\" or \"no\"");
    }
    public void tellPlayerLetterExists(Player player, ArrayList<Character> guessedLetter) {
        System.out.println(player.getName() + ", you have already guessed at these letters: " + guessedLetter);
    }
    public boolean forcePlayerAnswer(Player player) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print(player.getName() + ", i demand your answer");
            String answer = input.next().toLowerCase();
            try {
                if (answer.equalsIgnoreCase("yes")) {
                    return true;
                } else if (answer.equalsIgnoreCase("no")) {
                    return false;
                }
            } catch (Exception e) {
                System.out.print("I demand an answer: ");
            }
        }
    }
}