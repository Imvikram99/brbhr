package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    private String applicantName;
    private String applicantEmail;
    private String resume; // URL or base64 encoded string
    private String coverLetter;
}
