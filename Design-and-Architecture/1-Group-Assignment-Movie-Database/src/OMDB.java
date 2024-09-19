import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class OMDB {
    String apiKey;
private Application app; // JT
    public OMDB(String key) {
        apiKey = key;
    }

    public JSONObject askForInfo(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader inStream = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = inStream.readLine()) != null) {
                response.append(inputLine);
            }
            inStream.close();
            return new JSONObject(response.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
// Gjorde små ändringar i denna metod för att kunna söka på titel och inparameter. JT
    public JSONObject searchTitle(String movieTitle) { // S
        String title = movieTitle;
    String apiUrl = "http://www.omdbapi.com/?apikey=" + apiKey + "&t=" + title;
    return askForInfo(apiUrl);
}

    public Movie addMovieToDB(JSONObject movie) {
        String title = movie.getString("Title");
        String year = movie.getString("Year");
        String genre = movie.getString("Genre");
        String director = movie.getString("Director");
        String actors = movie.getString("Actors");
        String plot = movie.getString("Plot");
        return new Movie(title, year, genre, director, actors, plot);
    }

}

