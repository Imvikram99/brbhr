package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {
    // Define additional custom query methods if necessary
    // Example:
     List<JobApplication> findByApplicantEmail(String applicantEmail);
    List<JobApplication> findByAppliedToJobIdAndIsHiredTrue(String appliedToJobId);

    List<JobApplication> findByAppliedToJobIdInAndIsHiredTrue(List<String> jobIds);

    List<JobApplication> findByJobSeekerId(String jobSeekerId);
}
