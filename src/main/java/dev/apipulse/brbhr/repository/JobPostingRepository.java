package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.JobPosting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface JobPostingRepository extends MongoRepository<JobPosting, String> {
   // List<JobPosting> findInternalPostings();
}
