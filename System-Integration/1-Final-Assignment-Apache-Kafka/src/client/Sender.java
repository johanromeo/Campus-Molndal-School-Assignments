package client;

import java.io.IOException;

/**
 * Interface defining methods for sending JSON messages and serializing objects to JSON.
 */
public interface Sender {

    /**
     * Sends an HTTP POST request with a JSON message to a specified endpoint.
     *
     * @param jsonMessage The JSON message to be sent in the request body.
     * @param endpoint    The endpoint to which the request will be sent.
     * @throws IOException If an IO error occurs during the HTTP request.
     */
    void postRequest(String jsonMessage, String endpoint) throws IOException;

    /**
     * Serializes an object to a JSON string.
     *
     * @param dto The object to be serialized.
     * @return The JSON representation of the serialized object.
     */
    String serializeToJSON(Serialized dto);
}
