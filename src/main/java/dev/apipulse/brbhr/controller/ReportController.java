package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.Report;
import dev.apipulse.brbhr.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<Report> generateReport(@PathVariable String type) {
        Report report = reportService.generateReport(type);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/analytics")
    public ResponseEntity<AnalyticsResult> submitDataForAnalysis(@RequestBody AnalysisRequest request) {
        AnalyticsResult result = reportService.analyzeData(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
