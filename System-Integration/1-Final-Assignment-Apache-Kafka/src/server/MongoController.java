package com.johan.server.mongo.controller;

import com.johan.server.mongo.consumer.ReportRepository;
import com.johan.server.mongo.entities.ReportEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/** MongoController is used to set up API endpoints for the Mongo Admin to use when the need to
 * perform various operations arise */

@RestController
@RequestMapping("api/v1/mongo/reports")
public class MongoController {

    private final ReportRepository reportRepository;

    public MongoController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/get")
    public ResponseEntity<List<ReportEntity>> getReports() {
        try {
            List<ReportEntity> reports = reportRepository.findAll();
            return ResponseEntity.ok(reports);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/patch")
    public ResponseEntity<ReportEntity> updateReportIsSolved(@RequestParam String id) {
        try {
            Optional<ReportEntity> report = reportRepository.findById(id);
            report.get().setSolved(true);
            reportRepository.save(report.get());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ReportEntity> deleteReport(@RequestParam String id) {
        try {
            reportRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
