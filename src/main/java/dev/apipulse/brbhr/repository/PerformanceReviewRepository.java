package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.PerformanceReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PerformanceReviewRepository extends MongoRepository<PerformanceReview, String> {
    List<PerformanceReview> findByEmployeeId(String employeeId);
}
