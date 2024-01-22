package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.CompanyLeave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyLeaveRepository extends MongoRepository<CompanyLeave, String> {
}