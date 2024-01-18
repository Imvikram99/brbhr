package dev.apipulse.brbhr.service;

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

    public void applyForJob(Long jobId, JobApplication application) {
        // Logic to handle job application
        JobPosting jobPosting = jobPostingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Posting not found"));
        jobApplicationRepository.save(application);
    }
}
