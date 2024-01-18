package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class Offer {
    @Id
    private String id;
    private String candidateId;
    private String jobId;
    private Date offerDate;
    private Date validUntil;
    private String offerDetails; // Details of the job offer, like salary, position, etc.
    private String status; // e.g., Pending, Accepted, Declined

    // Standard getters and setters
}
