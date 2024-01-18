package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Report {
    @Id
    private String id;
    private String type;
    private String content; // This could be JSON, XML, etc., depending on your reporting format
    // Other relevant fields

    // Getters and setters
}
