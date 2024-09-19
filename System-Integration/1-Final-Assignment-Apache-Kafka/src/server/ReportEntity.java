package com.johan.server.mongo.entities;

import com.johan.pojos.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Entity class representing a report in a MongoDB collection.
 */
@Setter
@Getter
@NoArgsConstructor
@Document(collection = "reports")
public class ReportEntity {

    /**
     * The unique identifier for the report.
     */
    @Id
    private String id;
    private Person reportingPerson;
    private Person reportedPerson;
    private String eventDetails;
    private boolean solved;
}
