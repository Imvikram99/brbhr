package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class ExitProcess {
    @Id
    private String id;
    private String employeeId;
    private Date exitDate;
    private String reason; // Reason for leaving
    // Other fields related to the offboarding process

    // Getters and setters
}
