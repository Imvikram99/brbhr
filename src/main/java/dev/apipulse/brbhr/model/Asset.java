package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    @Id
    private String id;
    private String employeeId;
    private String assetType;
    private String assetName;
    private String serialNumber;

}
