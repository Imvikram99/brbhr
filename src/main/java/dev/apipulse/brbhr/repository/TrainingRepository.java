package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.TrainingProgram;

public interface TrainingRepository extends MongoRepository<TrainingProgram, String> {
    // Additional query methods as required
}
