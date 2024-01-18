package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class WellnessActivity {
    @Id
    private String id;
    private String employeeId;
    private String activityType;
    private Date activityDate;
    private Integer duration; // Duration in minutes
    // Other relevant fields

    // Getters and setters
}
