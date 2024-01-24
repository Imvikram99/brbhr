package dev.apipulse.brbhr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

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
    private RecruitmentStage currentRecruitmentStage;
    private String address;
    private String pinCode;
    private String nationality;
    private String mobileNumber;
    private String countryCode;
    private String visaStatus;
    private String country;
    private String appliedToJobId;
    @JsonIgnore
    private Boolean isHired;
}

