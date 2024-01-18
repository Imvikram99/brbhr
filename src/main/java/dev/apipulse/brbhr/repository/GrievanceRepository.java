package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.Grievance;

public interface GrievanceRepository extends MongoRepository<Grievance, String> {
    List<Grievance> findByEmployeeId(String employeeId);
}
