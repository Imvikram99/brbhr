package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.TrainingProgram;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TrainingRepository extends MongoRepository<TrainingProgram, String> {
    // Additional query methods as required
}
