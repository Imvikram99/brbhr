package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Candidate;
import dev.apipulse.brbhr.model.CompanyLeave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {
}
