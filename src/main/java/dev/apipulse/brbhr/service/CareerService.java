package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.JobApplication;
import dev.apipulse.brbhr.model.JobPosting;
import dev.apipulse.brbhr.repository.JobApplicationRepository;
import dev.apipulse.brbhr.repository.JobPostingRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerService {

    private final JobPostingRepository jobPostingRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public CareerService(JobPostingRepository jobPostingRepository, JobApplicationRepository jobApplicationRepository) {
        this.jobPostingRepository = jobPostingRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public List<JobPosting> getInternalJobPostings() {
        return jobPostingRepository.findInternalPostings();
    }

    public void applyForJob(String jobId, JobApplication application) {
        // Logic to handle job application
        JobPosting jobPosting = jobPostingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Posting not found"));
        jobApplicationRepository.save(application);
    }
}
