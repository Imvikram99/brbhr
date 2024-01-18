package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.ExitProcess;

public interface ExitProcessRepository extends MongoRepository<ExitProcess, String> {
    ExitProcess findByEmployeeId(String employeeId);
}
