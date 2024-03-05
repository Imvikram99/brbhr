package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.JobSeeker;
import dev.apipulse.brbhr.model.Recruiter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterProfileRepository extends MongoRepository<Recruiter, String> {
    
    // Find a JobSeeker by their email
    Optional<Recruiter> findByEmail(String email);
    Optional<Recruiter> findByUserName(String userName);

}
