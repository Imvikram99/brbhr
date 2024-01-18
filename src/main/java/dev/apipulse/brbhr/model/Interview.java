package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class Interview {
    @Id
    private String id;
    private String candidateId;
    private String jobId;
    private Date interviewDate;
    private String interviewer;
    private String interviewMode; // e.g., In-person, Video Call
    private String location;
    // Other relevant fields like interview status, notes, etc.

    // Standard getters and setters
}
