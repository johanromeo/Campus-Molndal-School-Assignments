import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
public class MongoConnector {
    private final String connectionString = "mongodb://localhost:27017";
    MongoClient client;
    public boolean connectToLocalMongoDB() {
        boolean isConnected;
        try {
            client = MongoClients.create(connectionString);
            isConnected = true;
        } catch (MongoClientException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        }
        return isConnected;
    }

    /**Har JÄTTESVÅRT att connecta och få det att funka med Atlas Cloud tyvärr. Vet ej hur jag ska fixa detta. Hjälp?*/
//    public MongoConnector() {
//         mongoConnectionStringReader = new MongoConnectionStringReader("mongo");

//    }
//    public void connectToMongoCloud() {
//        ServerApi serverApi = ServerApi.builder()
//                .version(ServerApiVersion.V1)
//                .build();
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(mongoConnectionStringReader.getMongoConnString()))
//                .serverApi(serverApi)
//                .build();
//
//        try (MongoClient mongoClient = MongoClients.create(settings)) {
//            try {
//                // Send a ping to confirm a successful connection
//                MongoDatabase database = mongoClient.getDatabase("admin");
//                database.runCommand(new Document("ping", 1));
//                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
//            } catch (MongoException e) {
//                connectToLocalMongoDB();
//                e.printStackTrace();
//            }
//        }

//    }

}
