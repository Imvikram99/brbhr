package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.*;
import dev.apipulse.brbhr.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@Slf4j
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @PostMapping("/post-job")
    public ResponseEntity<JobPosting> postJob(@RequestBody JobPosting jobPosting) {
        JobPosting postedJob = recruitmentService.postJob(jobPosting);
        return ResponseEntity.ok(postedJob);
    }

    @GetMapping("/job-postings")
    public ResponseEntity<List<JobPosting>> getAllJobPostings() {
        List<JobPosting> postings = recruitmentService.getAllJobPostings();
        return ResponseEntity.ok(postings);
    }

    @PostMapping("/job-postings/{jobPostingId}/add-stage")
    public ResponseEntity<JobPosting> addStageToJobPosting(@PathVariable String jobPostingId, @RequestBody RecruitmentStage recruitmentStage) {
        JobPosting updatedJobPosting = recruitmentService.addStageToJobPosting(jobPostingId, recruitmentStage);
        return ResponseEntity.ok(updatedJobPosting);
    }

    @PostMapping("/job-postings/close/{jobPostingId}")
    public ResponseEntity<Boolean> closeJobPosting(@PathVariable String jobPostingId) {
        recruitmentService.closeJobPosting(jobPostingId);
        return ResponseEntity.ok(Boolean.TRUE);
    }
    @PostMapping("/job-postings/open/{jobPostingId}")
    public ResponseEntity<Boolean> openJobPosting(@PathVariable String jobPostingId) {
        recruitmentService.openJobPosting(jobPostingId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/job-postings/{jobPostingId}/stages")
    public ResponseEntity<List<RecruitmentStage>> getStagesForJobPosting(@PathVariable String jobPostingId) {
        List<RecruitmentStage> recruitmentStages = recruitmentService.getStagesForJobPosting(jobPostingId);
        return ResponseEntity.ok(recruitmentStages);
    }

    @DeleteMapping("/job-postings/{jobPostingId}/stages/{stageId}")
    public ResponseEntity<?> deleteStageFromJobPosting(@PathVariable String jobPostingId, @PathVariable String stageId) {
        recruitmentService.deleteStageFromJobPosting(jobPostingId, stageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/applications/{applicationId}/current-stage")
    public ResponseEntity<RecruitmentStage> getCurrentStageOfApplication(@PathVariable String applicationId) {
        RecruitmentStage currentRecruitmentStage = recruitmentService.getCurrentStageOfApplication(applicationId);
        return ResponseEntity.ok(currentRecruitmentStage);
    }

    @PutMapping("/applications/{applicationId}/update-stage")
    public ResponseEntity<JobApplication> updateCandidateStage(@PathVariable String applicationId, @RequestBody RecruitmentStage newRecruitmentStage) {
        JobApplication updatedApplication = recruitmentService.updateCandidateStage(applicationId, newRecruitmentStage);
        return ResponseEntity.ok(updatedApplication);
    }

    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyForJob(@RequestBody JobApplication application) {
        JobApplication submittedApplication = recruitmentService.submitApplication(application);
        return ResponseEntity.ok(submittedApplication);
    }

    @GetMapping("/applications/{emailId}")
    public ResponseEntity<List<JobApplication>> getCandidateApplications(@PathVariable String applicantEmail) {
        List<JobApplication> applications = recruitmentService.getCandidateApplicationsByEmailId(applicantEmail);
        return ResponseEntity.ok(applications);
    }
    @GetMapping("/applications")
    public ResponseEntity<List<JobApplication>> getCandidateApplicationsAll() {
        List<JobApplication> applications = recruitmentService.getCandidateApplications();
        return ResponseEntity.ok(applications);
    }

    @PostMapping("/schedule-interview")
    public ResponseEntity<Interview> scheduleInterview(@RequestBody Interview interview) {
        Interview scheduledInterview = recruitmentService.scheduleInterview(interview);
        return ResponseEntity.ok(scheduledInterview);
    }

    @PostMapping("/extend-offer")
    public ResponseEntity<Offer> extendOffer(@RequestBody Offer offer) {
        Offer extendedOffer = recruitmentService.extendOffer(offer);
        return ResponseEntity.ok(extendedOffer);
    }

    @PostMapping("/accept-offer/{offerId}")
    public ResponseEntity<Offer> acceptOffer(@PathVariable String offerId) {
        Offer acceptedOffer = recruitmentService.acceptOffer(offerId);
        return ResponseEntity.ok(acceptedOffer);
    }

}
