package dev.apipulse.brbhr.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.apipulse.brbhr.model.*;
import dev.apipulse.brbhr.repository.CandidateRepository;
import dev.apipulse.brbhr.repository.OnboardingRepository;
import dev.apipulse.brbhr.sequence.SequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OnboardingService {
    @Autowired
    private OnboardingRepository onboardingRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired EmployeeService employeeService;

    @Autowired
    SequenceService sequenceService;

    public void createOnboardingStage(OnboardingStage stage,String jobId,Integer index) {
        // Validate and save the onboarding stage
        OnBoardingProcess onBoardingProcess = onboardingRepository.findOnboardingProcessByJobId(jobId);
        onBoardingProcess.insertStage(index,stage);
        onboardingRepository.save(onBoardingProcess);
    }
    public void updateCandidateStage(String candidateId, Integer fromStageIndex, Integer toStageIndex, String jobId, String managerId) {
        OnBoardingProcess onBoardingProcess = onboardingRepository.findOnboardingProcessByJobId(jobId);

        if (onBoardingProcess == null) {
            // Handle the case where the onboarding process is not found
            return;
        }

        List<OnboardingStage> stages = onBoardingProcess.getOnboardingStages();
        if (stages == null || fromStageIndex < 0 || toStageIndex < 0 || fromStageIndex >= stages.size() || toStageIndex >= stages.size()) {
            // Handle invalid stage indices
            return;
        }

        OnboardingStage fromStage = stages.get(fromStageIndex);
        OnboardingStage toStage = stages.get(toStageIndex);

        if (fromStage != null && toStage != null) {
            Map<String, String> fromStageCandidates = fromStage.getCandidateIdVsManager();
            Map<String, String> toStageCandidates = toStage.getCandidateIdVsManager();

            if (fromStageCandidates != null && fromStageCandidates.containsKey(candidateId)) {
                fromStageCandidates.remove(candidateId);
                toStageCandidates.put(candidateId, managerId);
                if(toStage.getType()==OnboardingStageType.COMPLETE){
                    Employee employee = new Employee();
                    employee.setEmpId(employee.getEmpId());
                    employeeService.createEmployee(employee);
                }
            } else {
                // Handle case where candidate is not found in the 'from' stage
            }
        } else {
            // Handle null stages
        }

        // Save the updated onboarding process
        onboardingRepository.save(onBoardingProcess);
    }

    public void triggerOnboarding(List<JobApplication> jobApplications,String appliedToJobId) {
        List<Candidate> candidates = new ArrayList<>();
        OnBoardingProcess onBoardingProcess = onboardingRepository.findOnboardingProcessByJobId(appliedToJobId);
        if (onBoardingProcess == null) {
            onBoardingProcess = new OnBoardingProcess();
            onBoardingProcess.setJobPostingId(appliedToJobId);
            OnboardingStage initialStage = new OnboardingStage();
            initialStage.setName("preArrival");
            OnboardingStage finalStage = new OnboardingStage();
            finalStage.setName("onboarded");
            onBoardingProcess.setOnboardingStages(Arrays.asList(initialStage,finalStage));
        }
        Map<String,String> candidateVsManager = new HashMap<>();
        for (JobApplication jobApplication : jobApplications) {
            Candidate candidate = new Candidate();
            candidate.setId(UUID.randomUUID().toString());
            candidate.setEmpId(sequenceService.getNextSequence("empIdSequence"));
            candidate.setJobApplication(jobApplication);
            candidate.setJobApplicationId(jobApplication.getId());// Set job application related details
            candidates.add(candidate);
            candidateVsManager.put(candidate.getId(),"");
        }
        OnboardingStage preArrivalStage = onBoardingProcess.getOnboardingStages().get(0);
        preArrivalStage.setCandidateIdVsManager(candidateVsManager);
        onboardingRepository.save(onBoardingProcess);
        // Save all candidates
        candidateRepository.saveAll(candidates);

        // Send mail to all candidates (This part can be implemented as per your email service logic)
        // sendOnboardingEmails(candidates);
    }

    public List<OnboardingStage> getCandidatesByStagesForJobPosting(String jobPostingId) {

        OnBoardingProcess onBoardingProcess = onboardingRepository.findOnboardingProcessByJobId(jobPostingId);
        if (onBoardingProcess != null) {
            List<OnboardingStage> stages = onBoardingProcess.getOnboardingStages();
            return stages;
        }
        return null;
    }

    public List<Candidate> getAllOnboardingInitiatedCandidates(String jobId){
        List<Candidate> onboarding = candidateRepository.findByJobApplication_AppliedToJobIdAndJobApplication_CurrentRecruitmentStage_RecruitmentStageType(jobId,RecruitmentStageType.HIRED.toString());
        return onboarding;
    }

}
