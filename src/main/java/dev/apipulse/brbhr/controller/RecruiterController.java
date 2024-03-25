package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.Recruiter;
import dev.apipulse.brbhr.security.User;
import dev.apipulse.brbhr.service.RecruiterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/recruiters")
@Slf4j
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/register")
    public ResponseEntity<?> registerRecruiter(@AuthenticationPrincipal User userDetails, @RequestBody Recruiter recruiter) {
        try {
            recruiter.setUserName(userDetails.getUsername());
            Recruiter registeredRecruiter = recruiterService.registerRecruiter(recruiter);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredRecruiter);
        } catch (DuplicateKeyException e) {
            String errorMessage = "Registration failed due to a duplicate key error.";
            if (e.getMessage() != null) {
                if (e.getMessage().contains("UserName")) {
                    errorMessage = "Registration failed: Already registered with this username.";
                }
            }
            Map<String, String> response = new HashMap<>();
            response.put("message", errorMessage);
            response.put("success", "false");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", "An error occurred during registration.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
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
