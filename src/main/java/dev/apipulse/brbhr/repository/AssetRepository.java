package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface AssetRepository extends MongoRepository<Asset, String> {
    List<Asset> findByEmployeeId(String employeeId);
}
