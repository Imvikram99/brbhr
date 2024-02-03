package dev.apipulse.brbhr.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface EmpIdSequenceRepository extends MongoRepository<EmpIdSequence, String> {
    // This method is for fetching the current sequence value
    // Actual increment logic will be handled separately
    @Query("{ '_id': ?0 }")
    Optional<EmpIdSequence> findById(String id);
}
