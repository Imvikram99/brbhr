package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.LegalDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LegalDocumentRepository extends MongoRepository<LegalDocument, String> {
    List<LegalDocument> findByOwnerId(String ownerId);
}
