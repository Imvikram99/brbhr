package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import dev.apipulse.brbhr.model.Interview;

public interface InterviewRepository extends MongoRepository<Interview, String> {
    // Query methods for handling interview data
}
