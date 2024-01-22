package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveDetails {
    private String empId;
    private String type;
    private Integer availableLeaves;
    private Integer carryForwardLeaves;
    private Integer totalLeaves;
}