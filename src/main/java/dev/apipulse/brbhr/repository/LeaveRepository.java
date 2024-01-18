package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.Leave;

public interface LeaveRepository extends MongoRepository<Leave, String> {
    List<Leave> findByEmployeeId(String employeeId);
}
