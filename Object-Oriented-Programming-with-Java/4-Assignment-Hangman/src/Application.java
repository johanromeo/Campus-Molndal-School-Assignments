import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private final WordList wordList;
    private Word word;
    private final Output output;
    private final Menu menu;
    private Player player;
    private ArrayList<Character> guessedLetters;
    private char guessedLetter;

    public Application(WordList wordList) {
        this.wordList = wordList;
        this.output = new Output();
        this.menu = new Menu();
    }

    public void initializeProgram() {
        Scanner input = new Scanner(System.in);
        output.greetPlayer();
        player = new Player(input.nextLine());
        output.informPlayer(player);
    }

    public void enterHogwarts() {
        boolean isDone = false;
        while (!isDone) {
            menu.showMenu();
            int userOption = menu.playerMenuChoise();

            switch (userOption) {
                case 1 -> wordList.printList();
                case 2 -> wordList.addNameToList();
                case 3 -> wordList.removeNameFromList();
                case 4 -> startGuessing();
                case 5 -> isDone = true;
            }
        }
        System.out.println("Farewell " + player.getName() + "!");
    }

    private void startGuessing() {
        player.setGuesses(10);
        boolean theGameIsOn = true;
        while (theGameIsOn) {
            playGame();
            theGameIsOn = playAgain();
        }
    }

    private void playGame() {
        guessedLetters = new ArrayList<>();
        this.word = new Word(wordList.generateWord());
        output.tellPlayerNumberOfLetters(word, player);

        boolean gameOver = false;
        while (!gameOver) {
            guessLogic();
            word.displayLetter(guessedLetter);
            System.out.println(word.getGuess());
            if (playerHasWon()) {
                output.tellPlayerHeWon(player, word);
                gameOver = true;
            } else if (player.getGuesses() == 0) {
                output.tellPlayerHeLost(player, word);
                gameOver = true;
            }
        }
    }

    private boolean playAgain() {
        output.askPlayerForRematch();
        boolean rematch = output.forcePlayerAnswer(player);
        if (rematch) {
            player.setGuesses(10);
        } else {
            endGame();
        }
        return rematch;
    }

    private void guessLogic() {
        Scanner input = new Scanner(System.in);
        String guess = input.next();
        guessedLetter = guess.charAt(0);

        if (guessedLetters.contains(guessedLetter)) {
            output.tellPlayerLetterExists(player, guessedLetters);
            player.decreaseGuesses();
        } else {
            guessedLetters.add(guessedLetter);

            if (!word.matchGuess(guessedLetter)) {
                player.decreaseGuesses();
            }
        }
        output.guessesLeft(player);
    }

    private boolean playerHasWon() {
        return word.isGuessEqualsWord();
    }

    private void endGame() {
        output.tellPlayerGuesses(player);
    }
}
