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
public class TravelRequest {
    @Id
    private String id;
    private String employeeId;
    private String destination;
    private Date startDate;
    private Date endDate;
    private Double estimatedCost;
    // Other relevant fields

    // Getters and setters
}
