package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsResult {
    private String resultSummary; // A summary or conclusion from the analytics
    private Map<String, Object> detailedResults; // Detailed results, if necessary

    // Additional fields to provide more context or data about the analysis
}
