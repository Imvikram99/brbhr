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
public class AnalysisRequest {
    private String type; // Type of analysis to be performed
    private Map<String, Object> parameters; // Analysis parameters

    // Additional fields depending on what data is needed for analysis
}
