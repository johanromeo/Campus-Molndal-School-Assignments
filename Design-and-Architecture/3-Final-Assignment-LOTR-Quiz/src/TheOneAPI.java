import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TheOneAPI {

    private final String apiKey;

    public TheOneAPI() {
        this.apiKey = "Bearer xxxxxx";
    }
    // Making connection to the API and returning the JSON object
    public JSONObject callAPI(String endPoint) {
        try {
            // Creating the URL and making the connection to the API and using endpoint as parameter
            URL url = new URL("https://the-one-api.dev/v2/" + endPoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Setting the request method to GET and adding the API key to the request
            connection.setRequestMethod("GET");
            // Adding the API key to the request header as a Bearer token
            connection.setRequestProperty("Authorization", apiKey);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            return new JSONObject(response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Getting the quotes from the API and returning a list of QuoteModels
    public List<QuoteModel> getQuotesFromAPI() {
        JSONObject jsonResult = callAPI("quote");
        return QuoteModel.fromJson(jsonResult.toString());
    }
    // Getting the characters from the API and returning a list of CharacterModels
    public List<CharacterModel> getCharacterFromAPI() {
        JSONObject jsonresult = callAPI("character");
        return CharacterModel.fromJson(jsonresult.toString());
    }
}