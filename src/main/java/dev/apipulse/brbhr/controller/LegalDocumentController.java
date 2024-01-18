package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.LegalDocument;
import dev.apipulse.brbhr.service.LegalDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/legal-docs")
public class LegalDocumentController {

    private final LegalDocumentService legalDocumentService;

    public LegalDocumentController(LegalDocumentService legalDocumentService) {
        this.legalDocumentService = legalDocumentService;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LegalDocument>> getEmployeeDocuments(@PathVariable String employeeId) {
        List<LegalDocument> documents = legalDocumentService.getDocumentsByEmployee(employeeId);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<LegalDocument>> getCandidateDocuments(@PathVariable String candidateId) {
        List<LegalDocument> documents = legalDocumentService.getDocumentsByCandidate(candidateId);
        return ResponseEntity.ok(documents);
    }

    @PostMapping
    public ResponseEntity<LegalDocument> uploadDocument(@RequestBody LegalDocument document) {
        LegalDocument savedDocument = legalDocumentService.saveDocument(document);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{docId}")
    public ResponseEntity<LegalDocument> updateDocument(@PathVariable String docId, @RequestBody LegalDocument document) {
        LegalDocument updatedDocument = legalDocumentService.updateDocument(docId, document);
        return ResponseEntity.ok(updatedDocument);
    }

    @DeleteMapping("/{docId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String docId) {
        legalDocumentService.deleteDocument(docId);
        return ResponseEntity.noContent().build();
    }

}
