import java.util.List;

public class Application {

    private final MongoDB mongoDB;
    private final HandlerFacade facade;
    private final Game quizz;

    // instantiate all the needed objects
    public Application() {
        mongoDB = new MongoDB();
        //KeyReader keyReader = new KeyReader("lotr");
        TheOneAPI theOneApi = new TheOneAPI();
        CharactersHandler charHandler = new CharactersHandler(mongoDB, theOneApi);
        QuotesHandler quoteHandler = new QuotesHandler(mongoDB, theOneApi);
        CharQuoteHandler charQuoteHandler = new CharQuoteHandler(mongoDB);
        facade = new HandlerFacade(mongoDB, quoteHandler, charHandler, charQuoteHandler, charQuoteHandler);
        quizz = new Game();
    }
    // set up the database and the collections
    private void DBSetup() {
        facade.initializeMongo();
        facade.createCollections();
        facade.inserDocs();
        mongoDB.disconnect();
    }
    // start the program and show the main menu
    public void startProgram() {
        DBSetup();
        boolean isDone = false;

        while (!isDone) {
            Menu.showMainMenu();
            switch (Menu.getUserInput()) {
                case 1 -> playGame();
                case 2 -> facade.dropDatabase();
                case 3 -> isDone = true;
            }
        }
    }

    // play the game
    private void playGame() {
        List<CharQuoteModel> quotes = facade.getQuotes();
        quizz.playGame(quotes);
    }
}
