package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.AnalyticsResult;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AnalyticsResultRepository extends MongoRepository<AnalyticsResult, String> {
    // Custom query methods for AnalyticsResult
    // Example:
    // List<AnalyticsResult> findByResultSummaryContaining(String keyword);
}
