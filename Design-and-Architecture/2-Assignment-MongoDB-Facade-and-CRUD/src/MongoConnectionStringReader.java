import java.io.FileInputStream;
import java.util.Properties;

public class MongoConnectionStringReader {
    Properties prop;
    public MongoConnectionStringReader(String currentFile) {
        prop = new Properties();
        String userHome = System.getProperty("user.home");
        try {
            FileInputStream input = new FileInputStream(userHome + "/Documents/DBKey/" + currentFile + ".txt");
            prop.load(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
    public String getMongoConnString() {
        return prop.getProperty("ConnString");
    }
}

