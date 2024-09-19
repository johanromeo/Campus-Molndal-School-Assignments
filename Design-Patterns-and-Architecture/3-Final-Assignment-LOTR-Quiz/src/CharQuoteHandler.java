import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CharQuoteHandler implements HandlersMethods, Retrievable<CharQuoteModel> {
    private final MongoDB mongoDB;
    private final String charQuoteCollName = "CharQuotes";

    public CharQuoteHandler(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

    /**
     * Creating CharQuotes collection
     */
    @Override
    public void createCollection() {
        mongoDB.connect();
        try {
            mongoDB.getDatabase().createCollection(charQuoteCollName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Inserting documents into CharQuotes collection
     */
    @Override
    public void insertDoc() {
        MongoCollection<Document> charQuoteColl = mongoDB.getDatabase().getCollection(charQuoteCollName);
        List<CharQuoteModel> charQuotes = getDocsAndSend();

        try {
            for (CharQuoteModel charQuote : charQuotes) {
                Document doc = new Document()
                        .append("character", charQuote.getCharacter())
                        .append("quote", charQuote.getDialog());

                charQuoteColl.insertOne(doc);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Fetching documents from CharQuotes collection and sending a List of CharQuoteModel objects
     */
    @Override
    public List<CharQuoteModel> getDocsAndSend() { // Help from ChatGPT
        MongoCollection<Document> charColl = mongoDB.getDatabase().getCollection("Characters");
        MongoCollection<Document> quoteColl = mongoDB.getDatabase().getCollection("Quotes");

        List<CharQuoteModel> charQuotes = new ArrayList<>();

        try {
            MongoCursor<Document> quoteCursor = quoteColl.find().iterator();

            while (quoteCursor.hasNext()) {
                Document quoteDoc = quoteCursor.next();
                String quote = quoteDoc.getString("dialog");
                String characterId = quoteDoc.getString("character");

                Document characterDoc = charColl.find(Filters.eq("_id", characterId)).first();
                if (characterDoc != null) {
                    String characterName = characterDoc.getString("name");

                    CharQuoteModel charQuote = new CharQuoteModel(characterName, quote);
                    charQuotes.add(charQuote);
                }
            }

            quoteCursor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return charQuotes;
    }
}
