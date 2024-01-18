package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.Asset;

public interface AssetRepository extends MongoRepository<Asset, String> {
    List<Asset> findByEmployeeId(String employeeId);
}
