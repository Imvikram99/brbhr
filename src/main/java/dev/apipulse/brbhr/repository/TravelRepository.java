package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.TravelRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TravelRepository extends MongoRepository<TravelRequest, String> {
    List<TravelRequest> findByEmployeeId(String employeeId);
}
