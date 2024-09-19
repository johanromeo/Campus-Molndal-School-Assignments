package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.HttpHostConnectException;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

/**
 * Implementation class for sending HTTP POST requests to a Kafka API endpoint.
 */
@Slf4j
@Getter
public class ApacheKafkaAPI implements Sender {
    // variable to store the HTTP response code for testing purpose
    private int httpResponseCode;

    /**
     * Sends an HTTP POST request with a JSON message to a specified API endpoint.
     *
     * @param jsonMessage The JSON message to be sent in the request body.
     * @param endpoint    The endpoint to which the request will be sent.
     * @throws IOException If an IO error occurs during the HTTP request.
     */
    @Override
    public void postRequest(String jsonMessage, String endpoint) throws IOException {

        String baseURI = "http://localhost:8080/api/v1/reports/";
        String kafkaAPI = baseURI + endpoint;

        // creating an HTTP client and setting appropriate content type value to be sent
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(kafkaAPI);
            httpPost.setHeader("Content-Type", "application/json; utf-8");

            StringEntity stringEntity = new StringEntity(jsonMessage);
            httpPost.setEntity(stringEntity);

            // executing the POST request and checking the response code
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                httpResponseCode = response.getCode();
                if (httpResponseCode == 200)
                    log.info("Response code: " + httpResponseCode + " - POST request was successful");
            } catch (HttpResponseException | HttpHostConnectException e) {
                log.info("POST request was unsuccessful for reason -> " + e.getMessage(), e.getCause());
            }
        }
    }

    /**
     * Serializes an object to a JSON string.
     *
     * @param dto The object to be serialized.
     * @return The JSON representation of the serialized object.
     */
    @Override
    public String serializeToJSON(Serialized dto) {
        String jsonInputString = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            jsonInputString = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.info("JSON parsing exception -> " + e.getMessage());
        }
        return jsonInputString;
    }
}
