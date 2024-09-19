package client;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MongoAdminAPI {
    public List<String> getAllMongoDocs(String uri, String endpoint) {
        List<String> docs = new ArrayList<>(); // list to store all the docs from the GET-request

        try {
            // Creating a http client with appropriate GET-request method
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri+endpoint))
                    .GET()
                    .build();
            // Executing the request, and if 200, stores it in the docs list
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();


            if (response.statusCode() == 200)
                docs.add(responseBody);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            log.error("GET-request failed -> " + e.getMessage());
        }

        return docs;
    }

    public void patchMongoDoc(String uri, String endpoint, String id) {
        try {
            // Creating a http client with appropriate PATCH-request method
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri + endpoint + id))
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(id))
                    .header("Content-Type", "application/json")
                    .build();
            // Executing the request
            client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            log.error("PATCH-request failed ->" + e.getMessage());
        }
    }


    public void deleteMongoDoc(String uri, String endpoint, String id) {
        try {
            // Creating a http client with appropriate DELETE-request method
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri + endpoint + id))
                    .DELETE()
                    .build();
            // Executing the request
            client.send(request, HttpResponse.BodyHandlers.ofString());


        } catch (URISyntaxException | IOException | InterruptedException e) {
            log.error("Error when deleting -> " + e.getMessage());
        }
    }
}
