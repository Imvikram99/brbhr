package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.PerformanceReview;

public interface PerformanceReviewRepository extends MongoRepository<PerformanceReview, String> {
    List<PerformanceReview> findByEmployeeId(String employeeId);
}
