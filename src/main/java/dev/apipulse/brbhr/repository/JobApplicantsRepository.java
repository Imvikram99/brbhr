package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.JobApplicants;
import dev.apipulse.brbhr.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobApplicantsRepository extends MongoRepository<JobApplicants, String> {
    List<JobApplicants> findByJobSeekerId(String jobSeekerId);

    // Get all job applicants for a specific job
    List<JobApplicants> findByJobId(String jobId);

    // Check if a specific job seeker has applied for a specific job
    boolean existsByJobSeekerIdAndJobId(String jobSeekerId, String jobId);
}
