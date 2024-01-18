package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.SalaryAdvance;

public interface SalaryAdvanceRepository extends MongoRepository<SalaryAdvance, String> {
    List<SalaryAdvance> findByEmployeeId(String employeeId);
}
