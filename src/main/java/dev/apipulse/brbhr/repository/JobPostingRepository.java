package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.JobPosting;

public interface JobPostingRepository extends MongoRepository<JobPosting, String> {
    List<JobPosting> findInternalPostings();
}
