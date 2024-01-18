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
public class SalaryAdvance {
    @Id
    private String id;
    private String employeeId;
    private Double amount;
    private Date requestDate;
    private String status; // e.g., "Pending", "Approved", "Rejected"
    

    
}
