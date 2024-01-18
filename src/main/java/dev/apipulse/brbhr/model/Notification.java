package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class Notification {
    @Id
    private String id;
    private String employeeId;
    private String message;
    private Date timestamp;
    // Other relevant fields

    // Getters and setters
}
