package com.johan.server.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration class for defining a Kafka topic.
 */
@Configuration
public class TopicConfig {
    @Bean
    public NewTopic reportsTopic() {
        return TopicBuilder
                .name("disturbance-reports")
                .replicas(3) // The number of replicas for fault tolerance.
                .partitions(3) // The number of partitions for parallelism.
                .build();
    }
}
