package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.WellnessActivity;

public interface WellnessActivityRepository extends MongoRepository<WellnessActivity, String> {
    List<WellnessActivity> findByEmployeeId(String employeeId);
}
