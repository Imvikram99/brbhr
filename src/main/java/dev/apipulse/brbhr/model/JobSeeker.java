package dev.apipulse.brbhr.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobSeeker {
    @Id
    String id;
    String firstName;
    String lastName;
    @Indexed(unique = true)
    String userName; // Unique identifier for the job seeker
    @Indexed(unique = true)
    String email; // Contact email for the job seeker
    String phoneNumber; // Contact phone number for the job seeker
    String resumeLink; // Link to the job seeker's resume
    String coverLetterLink; // Link to the job seeker's cover letter
    List<String> skills; // List of skills
    List<Education> educationHistory; // Educational background
    List<WorkExperience> workExperience; // Work history
    List<String> certifications; // Certifications and awards
    List<Project> personalProjects; // Personal or academic projects
    String professionalSummary; // Brief professional summary
    String desiredJobTitle; // The job title the seeker is aiming for
    List<String> languages; // Languages spoken
    String linkedInProfile; // LinkedIn profile link
    String portfolioLink; // Link to personal portfolio or website
    String githubLink; // GitHub or other version control profile link
    String twitterHandle; // Twitter handle, if applicable
    String availability; // Availability for work (e.g., immediately, within a month)
    List<String> preferredLocations; // Preferred work locations
    Boolean willingToRelocate; // Whether the job seeker is willing to relocate
    Boolean seekingFullTime; // Whether seeking full-time employment
    Boolean seekingPartTime; // Whether seeking part-time employment
    Boolean seekingContract; // Whether open to contract positions
    List<String> industryPreferences; // Preferred industries
    List<String> companyPreferences; // Preferred companies

    Integer currentCtc;
    Integer expectedCtc;

    Address currentAddress;
    Address permanentAddress;

    Boolean isServingNoticePeriod;

    LocalDate dateOfJoiningAtNewOrg;

    Integer officialNoticePeriod;
    Boolean isNoticePeriodNegotiable;

    LocalDate earliestYouCanJoin;

}

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Address {
    String street;
    String city;
    String state;
    String postalCode;
    String country;
}

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Education {
    String degree;
    String fieldOfStudy;
    String institutionName;
    String graduationYear;
    // Additional details like GPA, honors, etc., can be included
}

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class WorkExperience {
    String companyName;
    String jobTitle;
    String startDate;
    String endDate;
    String description;
    // Additional details like achievements, technologies used, etc., can be included
}

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Project {
    String projectName;
    String projectDescription;
    String technologiesUsed;
    String projectLink; // Link to the project if available
    // Additional details like project duration, team size, role, etc., can be included
}
