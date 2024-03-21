package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.JobApplication;
import dev.apipulse.brbhr.model.JobSeeker;
import dev.apipulse.brbhr.repository.JobApplicationRepository;
import dev.apipulse.brbhr.repository.JobSeekerProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class JobSeekerService {

    @Autowired
    private JobSeekerProfileRepository jobSeekerProfileRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public JobSeeker createProfile(JobSeeker jobSeekerProfile) {
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }

    public JobApplication applyForJob(String jobSeekerId, JobApplication application) {
        // This assumes that the jobSeekerId is set on the application object, 
        // or that there's logic to associate the application with the jobSeekerId.
        return jobApplicationRepository.save(application);
    }

    public String uploadResume(String userName, MultipartFile file) {
        // Generate a unique filename or use the jobSeekerId for the storage
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            String fileId = fileStorageService.store(file.getInputStream(), filename,file.getContentType());
            // Update the job seeker profile with the resume link or filename
            JobSeeker profile = jobSeekerProfileRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Job seeker profile not found"));
            profile.setResumeLink(fileId); // Assuming a field for storing resume link
            jobSeekerProfileRepository.save(profile);
            return "File uploaded successfully: " + filename;
        } catch (IOException e) {
            log.error("Error uploading file", e);
            throw new RuntimeException("Error uploading file", e);
        }
    }

    public List<JobApplication> getApplicationsByJobSeekerId(String jobSeekerId) {
        return jobApplicationRepository.findByJobSeekerId(jobSeekerId);
    }

    public JobSeeker registerJobSeeker(JobSeeker jobSeeker) {
       return jobSeekerProfileRepository.save(jobSeeker);
   }

    public JobSeeker updateJobSeekerProfile(String jobSeekerId, JobSeeker jobSeeker) {
        return null;
    }

    public JobSeeker getJobSeekerProfile(String userName) {
        return jobSeekerProfileRepository.findByUserName(userName).orElseThrow();
    }

    public List<JobSeeker> getAllJobSeekers() {
        return null;
    }

    public Optional<JobSeeker> getJobSeekerById(String id) {
        return jobSeekerProfileRepository.findById(id);
    }

    // Additional service methods can be defined here based on requirements
}
