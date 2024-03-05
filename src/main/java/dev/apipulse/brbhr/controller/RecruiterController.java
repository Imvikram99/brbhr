package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.Recruiter;
import dev.apipulse.brbhr.security.User;
import dev.apipulse.brbhr.service.RecruiterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruiters")
@Slf4j
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/register")
    public ResponseEntity<Recruiter> registerRecruiter(@AuthenticationPrincipal User userDetails, @RequestBody Recruiter recruiter) {
        recruiter.setUserName(userDetails.getUsername());
        Recruiter registeredRecruiter = recruiterService.registerRecruiter(recruiter);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredRecruiter);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<Recruiter> updateRecruiterProfile(@AuthenticationPrincipal User userDetails, @RequestBody Recruiter recruiter) {
        Recruiter updatedRecruiter = recruiterService.updateRecruiterProfile(userDetails.getUsername(), recruiter);
        return ResponseEntity.ok(updatedRecruiter);
    }

    @GetMapping
    public ResponseEntity<Recruiter> getRecruiterProfile(@AuthenticationPrincipal User userDetails) {
        Recruiter recruiter = recruiterService.getRecruiterProfile(userDetails.getUsername());
        return ResponseEntity.ok(recruiter);
    }
}
