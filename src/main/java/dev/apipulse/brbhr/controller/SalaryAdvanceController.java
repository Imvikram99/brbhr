package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.SalaryAdvance;
import dev.apipulse.brbhr.service.SalaryAdvanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary/advance")
public class SalaryAdvanceController {

    private final SalaryAdvanceService salaryAdvanceService;

    public SalaryAdvanceController(SalaryAdvanceService salaryAdvanceService) {
        this.salaryAdvanceService = salaryAdvanceService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<SalaryAdvance>> getSalaryAdvancesByEmployee(@PathVariable String employeeId) {
        List<SalaryAdvance> advances = salaryAdvanceService.getSalaryAdvancesByEmployee(employeeId);
        return ResponseEntity.ok(advances);
    }

    @PostMapping
    public ResponseEntity<SalaryAdvance> requestSalaryAdvance(@RequestBody SalaryAdvance salaryAdvance) {
        SalaryAdvance advance = salaryAdvanceService.requestSalaryAdvance(salaryAdvance);
        return new ResponseEntity<>(advance, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaryAdvance> updateSalaryAdvance(@PathVariable String id, @RequestBody SalaryAdvance salaryAdvance) {
        SalaryAdvance updatedAdvance = salaryAdvanceService.updateSalaryAdvance(id, salaryAdvance);
        return ResponseEntity.ok(updatedAdvance);
    }
}
