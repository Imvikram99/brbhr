package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Payroll;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PayrollRepository extends MongoRepository<Payroll, String> {
    Payroll findByEmployeeId(String employeeId);
}
