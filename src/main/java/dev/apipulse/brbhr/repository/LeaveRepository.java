package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LeaveRepository extends MongoRepository<Leave, String> {
    List<Leave> findByEmployeeId(String employeeId);
}
