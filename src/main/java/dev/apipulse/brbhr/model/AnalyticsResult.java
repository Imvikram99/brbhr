package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsResult {
    private String resultSummary; // A summary or conclusion from the analytics
    private Map<String, Object> detailedResults; // Detailed results, if necessary

    // Additional fields to provide more context or data about the analysis
}
