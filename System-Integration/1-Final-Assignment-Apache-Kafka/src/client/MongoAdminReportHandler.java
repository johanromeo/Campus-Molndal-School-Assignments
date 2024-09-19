package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * MongoAdminReportHandler lets the Mongo Admin perform various HTTP-requests on MongoAPI endpoints
 */
@Slf4j
public class MongoAdminReportHandler {
    private final Input input;
    private final MongoAdminAPI mongoAdminAPI;
    private final String uri = "http://localhost:8080/api/v1/mongo/reports";

    public MongoAdminReportHandler(Input input, MongoAdminAPI mongoAdminAPI) {
        this.input = input;
        this.mongoAdminAPI = mongoAdminAPI;
    }


    /**
     * Step 1:
     * Thanks to TypeReference, ObjectMapper knows how to deseralize the JSON-string into a list of ReportDTOs
     * Help from ChatGPT
     */

    private List<ReportDTO> convertJsonToReportDTO(String json) throws JsonProcessingException {
        List<ReportDTO> dtos;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ReportDTO>> typeReference = new TypeReference<>() {
        };
        dtos = objectMapper.readValue(json, typeReference);
        return dtos;
    }


    /**
     * Step 2:
     * The HTTP GET-request is called and joins the JSON-string into a single line and then converts it
     * into a ReportDTO, which gets added to the list.
     * This method is used for the methods below
     */

    public List<ReportDTO> fetchReportsForAdmin() throws JsonProcessingException {
        List<String> mongoDocs = mongoAdminAPI.getAllMongoDocs(uri, "/get");
        List<ReportDTO> dtos = new ArrayList<>();
        if (!mongoDocs.isEmpty()) {
            dtos = convertJsonToReportDTO(String.join("", mongoDocs));
        }
        return dtos;
    }

    /**
     * Step 3:
     * Checks if the reports list contains reports. If yes, method calls are enabled, if no, back to Mongo Admin Menu
     */
    private boolean hasReports(List<ReportDTO> reports) {
        return !reports.isEmpty();
    }

    /**
     * Step 4:
     * Checks if above method has reports. This, with hasReports(), are the main entry points to below methods and dictates
     * weather they can perform the methods or not
     */
    public boolean getAllReports() throws JsonProcessingException {
        if (hasReports(fetchReportsForAdmin())) {
            return true;
        } else {
            log.info("No reports in your MongoDB");
            return false;
        }
    }

    /**
     * Prints the reports with indexes
     */
    public void letAdminSeeIndexedReports() throws JsonProcessingException {
        if (getAllReports())
            Output.printIndexedReports(fetchReportsForAdmin());
    }

    /**
     * Returns a parsed string input, which later can be used to manipulate indexedReports
     */
    private int letAdminChoseIndex(String httpRequest, List<ReportDTO> indexedReports) {
        return Integer.parseInt(input.stringInput(
                "Enter index on the report you want to "
                        + httpRequest + " -> "));
    }

    /**
     * Letting the admin patch only if there are reports
     * Letting him chose which index to delete and pass the value as parameter in the http-method call
     */
    public void letAdminPatchReport() throws JsonProcessingException {
        if (getAllReports()) {
            // The Output class returns an indexed list of ReportDTO
            List<ReportDTO> indexedReports = Output.printIndexedReports(fetchReportsForAdmin());
            // letAdminChoseIndex returning the wanted report do delete
            int reportToPatchIndex;

            try {
                reportToPatchIndex = letAdminChoseIndex("patch", indexedReports);
                ReportDTO reportToPatch = indexedReports.get(reportToPatchIndex - 1);
                mongoAdminAPI.patchMongoDoc(uri, "/patch?id=", reportToPatch.getId());

            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                Output.printError("No such index exists\n");
            }
        }
    }

    /**Letting the admin delete only if there are reports
     *Letting him chose which index to delete and pass the value as parameter in the http-method call  */
    public void letAdminDeleteReport() throws JsonProcessingException {
        if (getAllReports()) {
            // The Output class returns an indexed list of ReportDTO
            List<ReportDTO> indexedReports = Output.printIndexedReports(fetchReportsForAdmin());

            int reportToDeleteIndex;
            try {
                // letAdminChoseIndex returning the wanted report do delete on indexedReports
                reportToDeleteIndex = letAdminChoseIndex("delete", indexedReports);
                ReportDTO reportToDelete = indexedReports.get(reportToDeleteIndex - 1);
                mongoAdminAPI.deleteMongoDoc(uri, "/delete?id=", reportToDelete.getId());
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                Output.printError("No such index exists\n");
            }
        }
    }
}