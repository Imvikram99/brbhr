package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DiversityMetrics {
    @Id
    private String id;
    private Integer totalEmployees;
    private Integer diversityCount; // Define as needed (e.g., gender, ethnicity)
    // Other relevant fields

    // Getters and setters
}
