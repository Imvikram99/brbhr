package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.JobSeeker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerProfileRepository extends MongoRepository<JobSeeker, String> {
    
    // Find a JobSeeker by their email
    Optional<JobSeeker> findByEmail(String email);
    Optional<JobSeeker> findByUserName(String userName);

}
