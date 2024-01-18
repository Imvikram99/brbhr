package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.Payroll;

public interface PayrollRepository extends MongoRepository<Payroll, String> {
    Payroll findByEmployeeId(String employeeId);
}
