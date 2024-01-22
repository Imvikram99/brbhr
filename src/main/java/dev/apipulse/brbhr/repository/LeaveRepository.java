package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends MongoRepository<Leave, String> {

    List<Leave> findByEmployeeId(String employeeId);

    // Custom query to find leave requests by employee ID
    List<Leave> findByEmployeeIdAndType(String employeeId,String type);

    List<Leave> findByAssignedToEmpId(String assignedToEmpId);
}

