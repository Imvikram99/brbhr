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
public class WellnessActivity {
    @Id
    private String id;
    private String employeeId;
    private String activityType;
    private Date activityDate;
    private Integer duration; // Duration in minutes
    

    
}
