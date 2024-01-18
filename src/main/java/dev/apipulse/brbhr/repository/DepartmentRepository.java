package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DepartmentRepository extends MongoRepository<Department, String> {
    // Additional query methods if required
}
