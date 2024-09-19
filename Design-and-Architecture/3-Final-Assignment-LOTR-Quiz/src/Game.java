import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Game { // ChatGPT helped me a lot with this logic.

    private final Random random;

    public Game() {
        random = new Random();
    }

    // This method is used to play the game and it takes a list of quotes as a parameter.
    public void playGame(List<CharQuoteModel> quotes) {
        int score = 0;
        // Player chooses how many quotes they want to play with.
        int quoteCount = askUserForQuoteNumber();

        // The game will randomly choose quotes from the list of quotes.
        List<CharQuoteModel> randomQuotes = getMeRandomQuotes(quotes, quoteCount);
        for (CharQuoteModel quote : randomQuotes) {
            System.out.println("Quote: " + quote.getDialog());
            // The game will randomly choose a character from the list of quotes.
            List<String> answerOptions = createAnswerOptions(quote.getCharacter(), quotes);
            Collections.shuffle(answerOptions);
            // The game will display the options for the player to choose from.
            System.out.println("Options:");
            for (int i = 0; i < answerOptions.size(); i++) {
                System.out.println((i + 1) + ". " + answerOptions.get(i));
            }
            // The player will choose an option.
            int playerGuess = getPlayerGuess();
            // The game will check if the player's guess is correct.
            if (answerOptions.get(playerGuess - 1).equalsIgnoreCase(quote.getCharacter())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + quote.getCharacter());
            }
        }

        System.out.println("Game Over! Your score: " + score + "/" + quoteCount);
    }
    // Uses the Menu class to get the user's input.
    private int askUserForQuoteNumber() {
        System.out.println("How many quotes do you want to play with?");
        return Menu.getUserInput();
    }

    // This method is used to get a random quote from the list of quotes.
    private List<CharQuoteModel> getMeRandomQuotes(List<CharQuoteModel> quotes, int count) {
        // The game will shuffle the list of quotes.
        Collections.shuffle(quotes);
        // The game will return the first quote from the list of quotes.
        return quotes.subList(0, count);
    }

    // This method is used to create the answer options for the player to choose from.
    private List<String> createAnswerOptions(String correctCharacter, List<CharQuoteModel> quotes) {
        List<String> answerOptions = new ArrayList<>();
        answerOptions.add(correctCharacter);

        // The game will get all the characters from the list of quotes.
        List<String> allCharacters = getAllCharacters(quotes);
        allCharacters.remove(correctCharacter);
        Collections.shuffle(allCharacters);
        answerOptions.addAll(allCharacters.subList(0, 4));

        return answerOptions;
    }

    // This method is used to get all the characters from the list of quotes.
    private List<String> getAllCharacters(List<CharQuoteModel> quotes) {
        List<String> characters = new ArrayList<>();
        for (CharQuoteModel quote : quotes) {
            if (!characters.contains(quote.getCharacter())) {
                characters.add(quote.getCharacter());
            }
        }
        return characters;
    }
    // Uses the Menu class to get the user's input.
    private int getPlayerGuess() {
        System.out.println("Enter your guess (1-5): ");
        return Menu.getUserInput();
    }
}
