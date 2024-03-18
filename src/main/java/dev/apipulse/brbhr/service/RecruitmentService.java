package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.*;
import dev.apipulse.brbhr.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RecruitmentService {

    @Autowired
    private JobPostingRepository jobPostingRepository;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    JobApplicantsRepository jobApplicantsRepository;
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private OfferRepository offerRepository;

    public List<JobPosting> getAllJobPostingsByRecruiterName(String userName) {
        return jobPostingRepository.findByRecruiterUserName(userName);
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
    public JobApplicants apply(JobApplicants application) {
        return jobApplicantsRepository.save(application);
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
            application.setIsHired(Boolean.TRUE);
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

    public List<JobApplication> getHiredCandidatesForJobIds(List<String> jobIds) {
        // Call the repository method to find hired candidates for the list of job IDs
        return jobApplicationRepository.findByAppliedToJobIdInAndIsHiredTrue(jobIds);
    }

    public void closeJobPosting(String jobPostingId) {
        Optional<JobPosting> jobPosting =jobPostingRepository.findById(jobPostingId);
        if(jobPosting.isPresent()) {
            jobPosting.get().setIsOpen(Boolean.FALSE);
            jobPostingRepository.save(jobPosting.get());
        }

    }

    public void openJobPosting(String jobPostingId) {
        Optional<JobPosting> jobPosting =jobPostingRepository.findById(jobPostingId);
        if(jobPosting.isPresent()) {
            jobPosting.get().setIsOpen(Boolean.TRUE);
            jobPostingRepository.save(jobPosting.get());
        }
    }
}
