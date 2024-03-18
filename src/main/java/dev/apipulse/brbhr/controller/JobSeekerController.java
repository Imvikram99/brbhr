package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.JobApplicants;
import dev.apipulse.brbhr.model.JobApplication;
import dev.apipulse.brbhr.model.JobPosting;
import dev.apipulse.brbhr.model.JobSeeker;
import dev.apipulse.brbhr.security.User;
import dev.apipulse.brbhr.service.JobSeekerService;
import dev.apipulse.brbhr.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/jobseekers")
@Slf4j
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    RecruitmentService recruitmentService;

    @PostMapping("/register")
    public ResponseEntity<JobSeeker> registerJobSeeker(@AuthenticationPrincipal User userDetails,@RequestBody JobSeeker jobSeeker) {
        jobSeeker.setUserName(userDetails.getUsername());
        JobSeeker registeredJobSeeker = jobSeekerService.registerJobSeeker(jobSeeker);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredJobSeeker);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<JobSeeker> updateJobSeekerProfile(@AuthenticationPrincipal User userDetails, @RequestBody JobSeeker jobSeeker) {
        JobSeeker updatedJobSeeker = jobSeekerService.updateJobSeekerProfile(userDetails.getUsername(), jobSeeker);
        return ResponseEntity.ok(updatedJobSeeker);
    }

    @PostMapping("/upload-resume")
    public ResponseEntity<String> uploadResume(@AuthenticationPrincipal User userDetails, @RequestParam("file") MultipartFile file) {
        String username = userDetails.getUsername();
        String message = jobSeekerService.uploadResume(username, file);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<JobSeeker> getJobSeekerProfile(@AuthenticationPrincipal User userDetails) {
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerProfile(userDetails.getUsername());
        return ResponseEntity.ok(jobSeeker);
    }

    @PostMapping("/apply/{jobId}")
    public ResponseEntity<JobApplicants> applyForJob(@AuthenticationPrincipal User userDetails, @PathVariable String jobId) {
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerProfile(userDetails.getUsername());
        JobApplicants jobApplicants = new JobApplicants();
        jobApplicants.setJobSeeker(jobSeeker);
        jobApplicants.setJobId(jobId);
        JobApplicants submittedApplication = recruitmentService.apply(jobApplicants);
        return ResponseEntity.ok(submittedApplication);
    }

    @GetMapping("/all-jobs")
    public ResponseEntity<List<JobPosting>> getAllHiredCandidates() {
        List<JobPosting> openJobPostings = recruitmentService.getAllJobOpenPostings();
        return ResponseEntity.ok(openJobPostings);
    }
}
