package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Payroll {
    @Id
    private String id;
    private String employeeId;
    private Double salary;
    private String payPeriod;
    // Other relevant fields

    // Getters and setters
}
