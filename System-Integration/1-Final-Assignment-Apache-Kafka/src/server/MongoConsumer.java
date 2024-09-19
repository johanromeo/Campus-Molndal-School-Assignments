package com.johan.server.mongo.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johan.server.mongo.entities.ReportEntity;
import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for consuming messages from a Kafka topic and saving them to MongoDB.
 */
@Service
@Slf4j
public class MongoConsumer {
    private final ObjectMapper objectMapper;
    private final ReportRepository reportRepository;

    @Autowired
    public MongoConsumer(ObjectMapper objectMapper, ReportRepository reportRepository) {
        this.objectMapper = objectMapper;
        this.reportRepository = reportRepository;
    }

    /**
     * Kafka listener method that consumes messages from the "disturbance-reports" topic.
     *
     * @param jsonMessage The JSON message received from Kafka.
     */
    @KafkaListener(topics = "disturbance-reports", groupId = "mongo")
    public void mongoSave(String jsonMessage) {
        if (isJsonCorrect(jsonMessage)) {
            try {
                ReportEntity reportEntity = objectMapper.readValue(jsonMessage, ReportEntity.class);
                reportRepository.save(reportEntity);
                log.info("Entity sent to MongoDB");
            } catch (JsonProcessingException | MongoException e) {
                log.error("Consumer error -> ", e);
            }
        } else {
            log.info("Error with JSON parsing");
        }
    }

    /**
     * Checks if a JSON message is correct (not null or empty).
     *
     * @param jsonMessage The JSON message to be checked.
     * @return True if the JSON message is correct, otherwise false.
     */
    public boolean isJsonCorrect(String jsonMessage) {
        return jsonMessage != null && !jsonMessage.isEmpty();
    }
}
