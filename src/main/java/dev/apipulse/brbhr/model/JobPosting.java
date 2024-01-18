package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class JobPosting {
    @Id
    private String id;
    private String title;
    private String description;
    private Date postingDate;
    private Date closingDate;
    // Other relevant fields

    // Getters and setters
}
