package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.DiversityMetrics;

public interface DiversityMetricsRepository extends MongoRepository<DiversityMetrics, String> {
    DiversityMetrics findLatestMetrics();
}
