package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class Event {
    @Id
    private String id;
    private String name;
    private Date eventDate;
    private String location;
    // Other relevant fields

    // Getters and setters
}
