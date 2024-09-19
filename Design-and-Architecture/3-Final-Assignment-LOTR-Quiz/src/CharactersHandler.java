import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CharactersHandler implements HandlersMethods, Retrievable<CharacterModel>{
    private final MongoDB mongoDB;
    private final TheOneAPI theOneAPI;
    private final String charCollName = "Characters";

    public CharactersHandler(MongoDB mongoDB, TheOneAPI theOneAPI) {
        this.mongoDB = mongoDB;
        this.theOneAPI = theOneAPI;
    }

    /**
     * Creating Characters collection
     */
    @Override
    public void createCollection() {
        mongoDB.connect();
        try {
            mongoDB.getDatabase().getCollection(charCollName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserting documents into Characters collection
     */
    @Override
    public void insertDoc() {
        MongoCollection<Document> charColl = mongoDB.getDatabase().getCollection(this.charCollName);
        List<CharacterModel> characters = theOneAPI.getCharacterFromAPI();
        try {
            for (CharacterModel character : characters) {
                Document doc = new Document("_id", character.get_id())
                        .append("name", character.getName());
                charColl.insertOne(doc);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Fetching documents from Characters collection and sending a List of CharacterModel objects
     */
    @Override
    public List<CharacterModel> getDocsAndSend() {
        MongoCollection<Document> charColl = mongoDB.getDatabase().getCollection("Characters");
        FindIterable<Document> iterable = charColl.find();
        List<CharacterModel> characters = new ArrayList<>();
        for (Document doc : iterable) {
            CharacterModel character = new CharacterModel(doc.getString("_id"), doc.getString("name"));
            characters.add(character);
        }
        return characters;
    }
}
