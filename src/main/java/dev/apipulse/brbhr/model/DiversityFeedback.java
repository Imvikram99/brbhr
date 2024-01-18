package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiversityFeedback {
    private String feedback;
    private String employeeId; // Optional, depends on whether feedback is anonymous
}
