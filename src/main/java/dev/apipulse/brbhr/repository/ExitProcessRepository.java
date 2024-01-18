package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.ExitProcess;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ExitProcessRepository extends MongoRepository<ExitProcess, String> {
    ExitProcess findByEmployeeId(String employeeId);
}
