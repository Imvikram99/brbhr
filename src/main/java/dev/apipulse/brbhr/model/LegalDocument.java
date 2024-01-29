package dev.apipulse.brbhr.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LegalDocument {
    @Id
    private String id;
    private String ownerId; // Can be an employee or candidate ID
    private String title;
    private String documentType; // e.g., "Policy Acknowledgment", "Employment Contract"
    private String content; // The content of the document, possibly as a URL to a stored file

}
