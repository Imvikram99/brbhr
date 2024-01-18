package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.DiversityMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DiversityMetricsRepository extends MongoRepository<DiversityMetrics, String> {

}
