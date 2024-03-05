package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.Recruiter;
import dev.apipulse.brbhr.repository.RecruiterProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecruiterService {

    @Autowired
    private RecruiterProfileRepository recruiterProfileRepository;

    public Recruiter registerRecruiter(Recruiter recruiterProfile) {
        // Here, you might include logic to check if the recruiter already exists based on unique fields like email
        return recruiterProfileRepository.save(recruiterProfile);
    }

    public Recruiter updateRecruiterProfile(String userName, Recruiter recruiter) {
        // Fetch the existing profile using userName, update it with the new recruiter details, and save
        Recruiter existingRecruiter = recruiterProfileRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Recruiter profile not found"));
        // Assuming you have a method to update the fields of the existing recruiter with the fields from the new recruiter object
        updateExistingRecruiter(existingRecruiter, recruiter);
        return recruiterProfileRepository.save(existingRecruiter);
    }

    public Recruiter getRecruiterProfile(String userName) {
        return recruiterProfileRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Recruiter profile not found"));
    }

    // Helper method to update existing recruiter details with new values
    private void updateExistingRecruiter(Recruiter existing, Recruiter updates) {
        // Example: existing.setFullName(updates.getFullName());
        // Repeat for other fields to be updated
    }
}
