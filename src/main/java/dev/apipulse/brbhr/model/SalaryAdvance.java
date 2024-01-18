package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class SalaryAdvance {
    @Id
    private String id;
    private String employeeId;
    private Double amount;
    private Date requestDate;
    private String status; // e.g., "Pending", "Approved", "Rejected"
    // Other relevant fields

    // Getters and setters
}
