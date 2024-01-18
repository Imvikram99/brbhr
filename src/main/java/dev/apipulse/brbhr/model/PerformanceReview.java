package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class PerformanceReview {
    @Id
    private String id;
    private String employeeId;
    private Date reviewDate;
    private String reviewText;
    // Other relevant fields

    // Getters and setters
}
