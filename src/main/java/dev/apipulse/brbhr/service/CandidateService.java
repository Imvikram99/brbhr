package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.*;
import dev.apipulse.brbhr.repository.JobApplicationRepository;
import dev.apipulse.brbhr.repository.JobPostingRepository;
import dev.apipulse.brbhr.repository.InterviewRepository;
import dev.apipulse.brbhr.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CandidateService {

    @Autowired
    private JobPostingRepository jobPostingRepository;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private OfferRepository offerRepository;

    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    public JobPosting postJob(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }

    public JobApplication submitApplication(JobApplication application) {
        return jobApplicationRepository.save(application);
    }

    public List<JobApplication> getCandidateApplicationsByEmailId(String applicantEmail) {
        return jobApplicationRepository.findByApplicantEmail(applicantEmail);
    }

    public List<JobApplication> getCandidateApplications() {
        return jobApplicationRepository.findAll();
    }

    public Interview scheduleInterview(Interview interview) {
        return null;
    }

    public Offer extendOffer(Offer offer) {
        return null;
    }

    public Offer acceptOffer(String offerId) {
        return null;//
    }


    public Stage getCurrentStageOfApplication(String applicationId) {
        JobApplication application = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        return application.getCurrentStage();
    }

    public JobApplication updateCandidateStage(String applicationId, Stage newStage) {
        JobApplication application = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setCurrentStage(newStage);
        return jobApplicationRepository.save(application);
    }

    public void deleteStageFromJobPosting(String jobPostingId, String stageId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));
        jobPosting.getStages().removeIf(stage -> stage.getId().equals(stageId));
        jobPostingRepository.save(jobPosting);
    }

    public List<Stage> getStagesForJobPosting(String jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));
        return jobPosting.getStages();
    }

    public JobPosting addStageToJobPosting(String jobPostingId, Stage stage) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));
        jobPosting.getStages().add(stage);
        return jobPostingRepository.save(jobPosting);
    }

    // Implement methods for posting jobs, applying to jobs, scheduling interviews, extending offers, etc.

}
