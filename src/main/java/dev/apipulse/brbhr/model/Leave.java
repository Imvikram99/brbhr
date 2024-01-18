package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class Leave {
    @Id
    private String id;
    private String employeeId;
    private Date startDate;
    private Date endDate;
    private String type;
    // Other relevant fields

    // Getters and setters
}