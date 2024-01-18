package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.LegalDocument;
import dev.apipulse.brbhr.repository.LegalDocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LegalDocumentService {

    @Autowired
    private LegalDocumentRepository legalDocumentRepository;

    public List<LegalDocument> getDocumentsByOwner(String ownerId) {
        return legalDocumentRepository.findByOwnerId(ownerId);
    }

    public LegalDocument saveDocument(LegalDocument document) {
        try {
            return legalDocumentRepository.save(document);
        } catch (Exception e) {
            log.error("Error saving legal document: ", e);
            throw e; // or handle more gracefully
        }
    }

    public LegalDocument updateDocument(String docId, LegalDocument document) {
        if (!legalDocumentRepository.existsById(docId)) {
            // Handle "document not found" situation
            log.error("Document with ID: " + docId + " not found");
            throw new RuntimeException("Document not found"); // Custom exception recommended
        }

        document.setId(docId); // Ensure the correct document is updated
        try {
            return legalDocumentRepository.save(document);
        } catch (Exception e) {
            log.error("Error updating legal document: ", e);
            throw e; // or handle more gracefully
        }
    }

    public void deleteDocument(String docId) {
        try {
            legalDocumentRepository.deleteById(docId);
        } catch (Exception e) {
            log.error("Error deleting legal document: ", e);
            throw e; // or handle more gracefully
        }
    }

    public List<LegalDocument> getDocumentsByEmployee(String employeeId) {
        return null;
    }

    public List<LegalDocument> getDocumentsByCandidate(String candidateId) {
        return null;
    }
}
