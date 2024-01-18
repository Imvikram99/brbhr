package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class Grievance {
    @Id
    private String id;
    private String employeeId;
    private String description;
    private Date filedDate;
    private String status; // e.g., "Open", "Resolved", "Closed"
    // Other relevant fields

    // Getters and setters
}
