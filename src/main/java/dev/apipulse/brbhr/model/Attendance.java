package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Id
    private String id;
    private String employeeId;
    private String checkInTime;
    private LocalDate checkInDate;
    private String checkOutTime;
    private LocalDate checkOutDate;
    private LocalDate date;
    private String shift;
    private String workType; // e.g., WFH, Office
    private String overTime;
    private String minimumHour;
    private Boolean isValidated;
    private String validatedBy;
    private String validatorId;

}
