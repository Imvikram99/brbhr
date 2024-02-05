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
public class Leave {
    @Id
    private String id;
    private String employeeId;
    private Date startDate;
    private Date endDate;
    private String type;
    private String status;
    private String reason;
    private Integer requestedDays;
    private String approverEmpId;
    private String approverReason;
    private String assignedToEmpId;
    

    
}
