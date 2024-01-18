package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.JobApplication;
import dev.apipulse.brbhr.model.JobPosting;
import dev.apipulse.brbhr.service.CareerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/careers/internal")
public class InternalCareerController {

    private final CareerService careerService;

    public InternalCareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping
    public ResponseEntity<List<JobPosting>> getInternalJobPostings() {
        List<JobPosting> postings = careerService.getInternalJobPostings();
        return ResponseEntity.ok(postings);
    }

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<Void> applyForJob(@PathVariable String jobId, @RequestBody JobApplication application) {
        careerService.applyForJob(jobId, application);
        return ResponseEntity.noContent().build();
    }
}
