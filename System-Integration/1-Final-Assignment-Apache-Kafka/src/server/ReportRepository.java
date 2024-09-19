package com.johan.server.mongo.consumer;

import com.johan.server.mongo.entities.ReportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<ReportEntity, String> {
}
