package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
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
