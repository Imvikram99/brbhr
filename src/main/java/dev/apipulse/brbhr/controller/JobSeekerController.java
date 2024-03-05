package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.JobSeeker;
import dev.apipulse.brbhr.security.User;
import dev.apipulse.brbhr.service.JobSeekerService;
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
}
