import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB implements DBUtils {
    private final String connString = "mongodb://localhost:27017";
    private MongoClient client;
    private MongoDatabase database;

    /**
     * Connecting to MongoDB
     */
    @Override
    public void connect() {
        try {
            client = MongoClients.create(connString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Disconnecting from MongoDB
     */
    @Override
    public void disconnect() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creating a database with a choosen name
     */
    @Override
    public void createDatabase() {
        database = client.getDatabase("lotrDB");
    }
    // Drops the database
    public void dropDatabase() {
        database.drop();
    }

    public String getConnString() {
        return connString;
    }

    public MongoClient getClient() {
        return client;
    }

    public void setClient(MongoClient client) {
        this.client = client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }
}
