package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.WellnessProgram;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface WellnessProgramRepository extends MongoRepository<WellnessProgram, String> {
    // Custom query methods for WellnessProgram
    // Example:
    // List<WellnessProgram> findByProgramName(String programName);
}
