package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.Report;

public interface ReportRepository extends MongoRepository<Report, String> {
    // Custom methods for report generation and retrieval
}
