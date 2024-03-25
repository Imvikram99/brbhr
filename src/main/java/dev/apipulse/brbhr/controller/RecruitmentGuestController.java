package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.JobPosting;
import dev.apipulse.brbhr.security.User;
import dev.apipulse.brbhr.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guest/recruitment")
@Slf4j
public class RecruitmentGuestController {

    @Autowired
    private RecruitmentService recruitmentService;
    @GetMapping("/job-postings/{jobPostingId}")
    public ResponseEntity<JobPosting> getJobPosting(@PathVariable String jobPostingId) {
        Optional<JobPosting> posting = recruitmentService.getJobPostingById(jobPostingId);
        if (posting.isPresent()) {
            return ResponseEntity.ok(posting.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
