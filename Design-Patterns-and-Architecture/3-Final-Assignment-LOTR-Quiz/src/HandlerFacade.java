import java.util.List;

public class HandlerFacade {
    private final HandlersMethods quoteHandler;
    private final HandlersMethods characterHandler;
    private final HandlersMethods charQuoteHandler;
    private final Retrievable<CharQuoteModel> injection;
    private final MongoDB mongoDB;

    // Gets all the handlers and the injection of the CharQuoteHandler
    public HandlerFacade(MongoDB mongoDB, HandlersMethods quoteHandler, HandlersMethods characterHandler,
                         HandlersMethods charQuoteHandler, Retrievable<CharQuoteModel> injection) {
        this.quoteHandler = quoteHandler;
        this.characterHandler = characterHandler;
        this.charQuoteHandler = charQuoteHandler;
        this.injection = injection;
        this.mongoDB = mongoDB;
    }

    // Initializes the MongoDB connection and creates the database
    public void initializeMongo() {
        mongoDB.connect();
        mongoDB.createDatabase();
    }
    // Creates the collections for the MongoDB
    public void createCollections() {
        quoteHandler.createCollection();
        characterHandler.createCollection();
        charQuoteHandler.createCollection();

    }
    // Inserts the documents into the MongoDB
    public void inserDocs() {
        quoteHandler.insertDoc();
        characterHandler.insertDoc();
        charQuoteHandler.insertDoc();
    }
    // Drops the database
    public void dropDatabase() {
        mongoDB.dropDatabase();
    }
    // Gets the quotes from the CharQuoteHandler
    public List<CharQuoteModel> getQuotes() {
        return injection.getDocsAndSend();
    }
}

