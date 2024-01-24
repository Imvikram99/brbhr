package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.*;
import dev.apipulse.brbhr.service.OnboardingService;
import dev.apipulse.brbhr.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    @Autowired
    OnboardingService onboardingService;

    @Autowired
    RecruitmentService recruitmentService;

    @PostMapping("/triggerOnboarding/{jobId}")
    public ResponseEntity<Boolean> triggerOnboarding(@RequestBody List<JobApplication> jobApplications,@PathVariable String jobId) {
        onboardingService.triggerOnboarding(jobApplications,jobId);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
    }

    @GetMapping("/candidates")
    public ResponseEntity<List<JobApplication>> getAllHiredCandidates() {
        List<JobPosting> openJobPostings = recruitmentService.getAllJobOpenPostings();

        if (openJobPostings.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<String> jobIds = openJobPostings.stream()
                .map(JobPosting::getId)
                .collect(Collectors.toList());

        List<JobApplication> candidates = recruitmentService.getHiredCandidatesForJobIds(jobIds);
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/stages/{jobId}")
    public ResponseEntity<List<OnboardingStage>> getAllOnboardingStages(@PathVariable String jobId) {
        return ResponseEntity.ok(onboardingService.getCandidatesByStagesForJobPosting(jobId));
    }

    @PostMapping("/stage/{jobId}")
    public ResponseEntity<Boolean> createOnboardingStage(@RequestBody OnboardingStage stage,@PathVariable String jobId,@RequestParam Integer index) {
        onboardingService.createOnboardingStage(stage,jobId,index);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
    }

    @PutMapping("/candidate/{candidateId}/stage")
    public ResponseEntity<?> updateCandidateStage(@PathVariable String candidateId, @RequestParam Integer fromStageIndex, @RequestParam Integer toStageIndex, @RequestParam String jobId, @RequestParam String managerId) {
        try {
            onboardingService.updateCandidateStage(candidateId, fromStageIndex, toStageIndex, jobId, managerId);
            return ResponseEntity.ok().body("Candidate stage updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


}
