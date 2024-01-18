package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.Grievance;
import dev.apipulse.brbhr.service.GrievanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grievances")
public class GrievanceController {

    private final GrievanceService grievanceService;

    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @PostMapping
    public ResponseEntity<Grievance> fileGrievance(@RequestBody Grievance grievance) {
        Grievance filedGrievance = grievanceService.fileGrievance(grievance);
        return new ResponseEntity<>(filedGrievance, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Grievance>> getGrievancesByEmployee(@PathVariable Long employeeId) {
        List<Grievance> grievances = grievanceService.getGrievancesByEmployee(employeeId);
        return ResponseEntity.ok(grievances);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grievance> updateGrievance(@PathVariable Long id, @RequestBody Grievance grievance) {
        Grievance updatedGrievance = grievanceService.updateGrievance(id, grievance);
        return ResponseEntity.ok(updatedGrievance);
    }
}
