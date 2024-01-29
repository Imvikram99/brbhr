package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.*;
import dev.apipulse.brbhr.repository.JobApplicationRepository;
import dev.apipulse.brbhr.repository.JobPostingRepository;
import dev.apipulse.brbhr.repository.InterviewRepository;
import dev.apipulse.brbhr.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RecruitmentService {

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

    public List<JobPosting> getAllJobOpenPostings() {
        return jobPostingRepository.findByIsOpenTrue();
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
        return null;
    }


    public RecruitmentStage getCurrentStageOfApplication(String applicationId) {
        JobApplication application = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        return application.getCurrentRecruitmentStage();
    }

    public JobApplication updateCandidateStage(String applicationId, RecruitmentStage newRecruitmentStage) {
        JobApplication application = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setCurrentRecruitmentStage(newRecruitmentStage);
        if(newRecruitmentStage.getRecruitmentStageType()==RecruitmentStageType.HIRED){
            application.setIsHired(true);
        }
        return jobApplicationRepository.save(application);
    }

    public void deleteStageFromJobPosting(String jobPostingId, String stageId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));
        jobPosting.getRecruitmentStages().removeIf(recruitmentStage -> recruitmentStage.getId().equals(stageId));
        jobPostingRepository.save(jobPosting);
    }

    public List<RecruitmentStage> getStagesForJobPosting(String jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));
        return jobPosting.getRecruitmentStages();
    }

    public JobPosting addStageToJobPosting(String jobPostingId, RecruitmentStage recruitmentStage) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));

        List<RecruitmentStage> stages = jobPosting.getRecruitmentStages();
        if (stages == null) {
            stages = new ArrayList<>();
            jobPosting.setRecruitmentStages(stages);
        }
        stages.add(recruitmentStage);

        return jobPostingRepository.save(jobPosting);
    }


    public List<JobApplication> getHiredCandidates(String jobId){
        return jobApplicationRepository.findByAppliedToJobIdAndIsHiredTrue(jobId);
    }

    public List<JobApplication> getHiredCandidatesForJobIds(List<String> jobIds) {
        // Call the repository method to find hired candidates for the list of job IDs
        return jobApplicationRepository.findByAppliedToJobIdInAndIsHiredTrue(jobIds);
    }

}
