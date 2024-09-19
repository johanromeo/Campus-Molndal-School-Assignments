package com.johan.server.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.NetworkException;
import org.apache.kafka.common.errors.RecordTooLargeException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for producing and sending JSON messages to a Kafka topic.
 */
@Service
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a JSON message to the "disturbance-reports" Kafka topic.
     *
     * @param jsonMessage The JSON message to be sent.
     */
    public void sendToTopic(String jsonMessage) {

        try {
            Message<String> message = MessageBuilder // setting the payload to be sent and the topic to send it to
                    .withPayload(jsonMessage)
                    .setHeader(KafkaHeaders.TOPIC, "disturbance-reports")
                    .build();

            kafkaTemplate.send(message);
            log.info("JSON message was successfully sent to the topic");
        } catch (
                KafkaProducerException | NetworkException |
                RecordTooLargeException | SerializationException |
                TimeoutException e
        ) {
            log.error("Producer error -> ", e);
        }
    }
}
