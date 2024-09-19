package client;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


/**JSONFormatter has only the static method to provide a more readable JSON-format for the Mongo Admin
 * and the Kafka ConsoleConsumer */

@Slf4j
public class JSONFormatter {
    public static String formatJSON(String toFormat) {
        String formattedJSON = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(toFormat);
            formattedJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (Exception e) {
            log.error("Error while formatting JSON -> " + e.getMessage());
        }
        return formattedJSON;
    }
}
