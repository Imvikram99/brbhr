package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Grievance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface GrievanceRepository extends MongoRepository<Grievance, String> {
    List<Grievance> findByEmployeeId(String employeeId);
}
