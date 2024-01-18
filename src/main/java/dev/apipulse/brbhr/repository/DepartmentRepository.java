package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.Department;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    // Additional query methods if required
}
