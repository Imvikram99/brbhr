package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.JobApplicants;
import dev.apipulse.brbhr.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobApplicantsRepository extends MongoRepository<JobApplicants, String> {
}
