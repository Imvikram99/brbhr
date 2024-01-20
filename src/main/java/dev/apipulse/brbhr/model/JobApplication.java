package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    @Id
    private String id;
    private String applicantName;
    private String applicantEmail;
    private String resume; // URL or base64 encoded string
    private String coverLetter;
    private Stage currentStage;
    private String address;
    private String pincode;
    private String nationality;
    private String mobileNumber;
    private String countryCode;
    private String visaStatus;
    private String country;
}

