package dev.apipulse.brbhr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Asset {
    @Id
    private String id;
    private String employeeId;
    private String assetType;
    private String assetName;
    private String serialNumber;
    // Other relevant fields

    // Getters and setters
}
