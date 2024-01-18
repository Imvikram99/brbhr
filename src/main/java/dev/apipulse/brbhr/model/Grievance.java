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
public class Grievance {
    @Id
    private String id;
    private String employeeId;
    private String description;
    private Date filedDate;
    private String status; // e.g., "Open", "Resolved", "Closed"
    

    
}
