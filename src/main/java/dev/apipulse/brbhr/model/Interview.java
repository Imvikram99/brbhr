package dev.apipulse.brbhr.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
