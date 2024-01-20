package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.*;
import dev.apipulse.brbhr.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@Slf4j
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/post-job")
    public ResponseEntity<JobPosting> postJob(@RequestBody JobPosting jobPosting) {
        JobPosting postedJob = candidateService.postJob(jobPosting);
        return ResponseEntity.ok(postedJob);
    }

    @GetMapping("/job-postings")
    public ResponseEntity<List<JobPosting>> getAllJobPostings() {
        List<JobPosting> postings = candidateService.getAllJobPostings();
        return ResponseEntity.ok(postings);
    }

    @PostMapping("/job-postings/{jobPostingId}/add-stage")
    public ResponseEntity<JobPosting> addStageToJobPosting(@PathVariable String jobPostingId, @RequestBody Stage stage) {
        JobPosting updatedJobPosting = candidateService.addStageToJobPosting(jobPostingId, stage);
        return ResponseEntity.ok(updatedJobPosting);
    }

    @GetMapping("/job-postings/{jobPostingId}/stages")
    public ResponseEntity<List<Stage>> getStagesForJobPosting(@PathVariable String jobPostingId) {
        List<Stage> stages = candidateService.getStagesForJobPosting(jobPostingId);
        return ResponseEntity.ok(stages);
    }

    @DeleteMapping("/job-postings/{jobPostingId}/stages/{stageId}")
    public ResponseEntity<?> deleteStageFromJobPosting(@PathVariable String jobPostingId, @PathVariable String stageId) {
        candidateService.deleteStageFromJobPosting(jobPostingId, stageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/applications/{applicationId}/current-stage")
    public ResponseEntity<Stage> getCurrentStageOfApplication(@PathVariable String applicationId) {
        Stage currentStage = candidateService.getCurrentStageOfApplication(applicationId);
        return ResponseEntity.ok(currentStage);
    }

    @PutMapping("/applications/{applicationId}/update-stage")
    public ResponseEntity<JobApplication> updateCandidateStage(@PathVariable String applicationId, @RequestBody Stage newStage) {
        JobApplication updatedApplication = candidateService.updateCandidateStage(applicationId, newStage);
        return ResponseEntity.ok(updatedApplication);
    }

    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyForJob(@RequestBody JobApplication application) {
        JobApplication submittedApplication = candidateService.submitApplication(application);
        return ResponseEntity.ok(submittedApplication);
    }

    @GetMapping("/applications/{emailId}")
    public ResponseEntity<List<JobApplication>> getCandidateApplications(@PathVariable String applicantEmail) {
        List<JobApplication> applications = candidateService.getCandidateApplicationsByEmailId(applicantEmail);
        return ResponseEntity.ok(applications);
    }
    @GetMapping("/applications")
    public ResponseEntity<List<JobApplication>> getCandidateApplicationsAll() {
        List<JobApplication> applications = candidateService.getCandidateApplications();
        return ResponseEntity.ok(applications);
    }

    @PostMapping("/schedule-interview")
    public ResponseEntity<Interview> scheduleInterview(@RequestBody Interview interview) {
        Interview scheduledInterview = candidateService.scheduleInterview(interview);
        return ResponseEntity.ok(scheduledInterview);
    }

    @PostMapping("/extend-offer")
    public ResponseEntity<Offer> extendOffer(@RequestBody Offer offer) {
        Offer extendedOffer = candidateService.extendOffer(offer);
        return ResponseEntity.ok(extendedOffer);
    }

    @PostMapping("/accept-offer/{offerId}")
    public ResponseEntity<Offer> acceptOffer(@PathVariable String offerId) {
        Offer acceptedOffer = candidateService.acceptOffer(offerId);
        return ResponseEntity.ok(acceptedOffer);
    }

    // Additional endpoints for updating/deleting job postings, interviews, offers, etc.

}
