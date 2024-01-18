package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.WellnessActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WellnessActivityRepository extends MongoRepository<WellnessActivity, String> {
    List<WellnessActivity> findByEmployeeId(String employeeId);
}
