package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TrainingProgram {
    @Id
    private String id;
    private String name;
    private String description;
    // Other relevant fields

    // Getters and setters
}
