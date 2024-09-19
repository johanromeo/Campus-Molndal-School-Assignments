import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class QuotesHandler implements HandlersMethods, Retrievable<QuoteModel> {
    private final MongoDB mongoDB;
    private final TheOneAPI theOneAPI;
    private final String quoteCollName = "Quotes";

    public QuotesHandler(MongoDB mongoDB, TheOneAPI theOneAPI) {
        this.mongoDB = mongoDB;
        this.theOneAPI = theOneAPI;
    }


    /**
     * Creating Quotes collection
     */
    @Override
    public void createCollection() {
        mongoDB.connect();
        try {
            mongoDB.getDatabase().createCollection(quoteCollName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserting documents into Quotes collection
     */
    @Override
    public void insertDoc() {
        MongoCollection<Document> quoteColl = mongoDB.getDatabase().getCollection(quoteCollName);
        List<QuoteModel> quotes = theOneAPI.getQuotesFromAPI();
        try {
            for (QuoteModel quote : quotes) {
                Document doc = new Document("dialog", quote.getDialog())
                        .append("character", quote.getCharacter());
                quoteColl.insertOne(doc);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Fetching documents from Quotes collection and sending a List of QuoteModel objects
     */
    @Override
    public List<QuoteModel> getDocsAndSend() {
        MongoCollection<Document> quoteColl = mongoDB.getDatabase().getCollection("Quotes");
        FindIterable<Document> iterable = quoteColl.find();
        List<QuoteModel> quotes = new ArrayList<>();
        for (Document doc : iterable) {
            QuoteModel quote = new QuoteModel(doc.getString("dialog"), doc.getString("character"));
            quotes.add(quote);
        }
        return quotes;
    }
}
