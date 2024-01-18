package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findByDepartmentId(Long departmentId);
    // Custom query methods can be defined here if needed
}
