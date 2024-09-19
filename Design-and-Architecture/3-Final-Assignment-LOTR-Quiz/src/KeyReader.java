import java.io.FileInputStream;
import java.util.Properties;

public class KeyReader {

    private final Properties prop;

    // Reads the API key from the file
    public KeyReader(String file) {
        prop = new Properties();
        // Gets the user home directory
        String userHome = System.getProperty("user.home");
        try {
            // Reads the file and loads the properties
            FileInputStream input = new FileInputStream(
                    userHome+"/Documents/APIkeys/"+file+".txt");
            prop.load(input);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // Returns the API key
    public String getAPIKey() {
        return prop.getProperty("apiKey");
    }
}