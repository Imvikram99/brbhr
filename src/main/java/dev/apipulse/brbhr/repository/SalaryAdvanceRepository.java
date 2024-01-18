package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.SalaryAdvance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface SalaryAdvanceRepository extends MongoRepository<SalaryAdvance, String> {
    List<SalaryAdvance> findByEmployeeId(String employeeId);
}
