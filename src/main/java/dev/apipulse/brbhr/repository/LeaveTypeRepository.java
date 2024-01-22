package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Leave;
import dev.apipulse.brbhr.model.LeaveTypeDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends MongoRepository<LeaveTypeDetail, String> {

}

