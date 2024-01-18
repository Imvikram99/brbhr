package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiversityMetrics {
    @Id
    private String id;
    private Integer totalEmployees;
    private Integer diversityCount; // Define as needed (e.g., gender, ethnicity)
    

    
}
