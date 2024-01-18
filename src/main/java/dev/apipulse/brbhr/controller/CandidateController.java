package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.JobApplication;
import dev.apipulse.brbhr.model.JobPosting;
import dev.apipulse.brbhr.model.Interview;
import dev.apipulse.brbhr.model.Offer;
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

    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyForJob(@RequestBody JobApplication application) {
        JobApplication submittedApplication = candidateService.submitApplication(application);
        return ResponseEntity.ok(submittedApplication);
    }

    @GetMapping("/applications/{candidateId}")
    public ResponseEntity<List<JobApplication>> getCandidateApplications(@PathVariable String candidateId) {
        List<JobApplication> applications = candidateService.getCandidateApplications(candidateId);
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
