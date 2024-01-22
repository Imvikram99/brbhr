package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Leave;
import dev.apipulse.brbhr.model.LeaveDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveDetailsRepository extends MongoRepository<LeaveDetails, String> {
    LeaveDetails findByEmpId(String employeeId);
}
