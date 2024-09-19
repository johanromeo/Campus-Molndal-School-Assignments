import java.io.FileInputStream;
import java.util.Properties;

public class KeyReader {
    Properties prop;

    public KeyReader(String currentFile) {
        prop = new Properties();
        String userHome = System.getProperty("user.home");
        try {
            FileInputStream input = new FileInputStream(userHome + "/Documents/API-keys/" + currentFile + ".txt");
            prop.load(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
    public String getAPIKey() {
        return prop.getProperty("apiKey");
    }

    public String getKey(String key) {
        return prop.getProperty(key);
    }
}
