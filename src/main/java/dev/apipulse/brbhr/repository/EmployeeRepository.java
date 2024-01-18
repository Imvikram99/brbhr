package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    // Custom query methods can be defined here if needed
}
