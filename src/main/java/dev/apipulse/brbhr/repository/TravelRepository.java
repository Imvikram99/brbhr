package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.TravelRequest;

public interface TravelRepository extends MongoRepository<TravelRequest, String> {
    List<TravelRequest> findByEmployeeId(String employeeId);
}
