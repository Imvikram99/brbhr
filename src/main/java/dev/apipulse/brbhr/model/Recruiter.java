package dev.apipulse.brbhr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Recruiter {
    @Id
    String id;
    String fullName; // Recruiter's full name
    //@Indexed(unique = true)
    String phoneNumber; // Contact phone number
    //@Indexed(unique = true)
    String workEmail; // Official work email (not personal like Gmail/Yahoo)
    String companyType; // Type of company (e.g., Startup, MNC, NGO, etc.)
    String organization; // Organization name
    String designation; // Designation within the organization
    String location; // Office location
    @Indexed(unique = true)
    String userName;

    String email;

}
