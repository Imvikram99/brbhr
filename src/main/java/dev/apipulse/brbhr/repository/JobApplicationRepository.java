package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {
    // Define additional custom query methods if necessary
    // Example:
    // List<JobApplication> findByApplicantName(String applicantName);
}
