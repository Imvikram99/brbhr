package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Candidate;
import dev.apipulse.brbhr.model.CompanyLeave;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {
    @Query("{ 'jobApplication.appliedToJobId' : ?0, 'jobApplication.isHired' : ?1 }")
    List<Candidate> findByJobApplication_AppliedToJobIdAndJobApplication_IsHired(String jobId, Boolean isHired);

    @Aggregation(pipeline = {
            "{ '$match': {'_id': 'empIdSequence'} }", // Match the document with the specific ID for empId sequence
            "{ '$project': {'maxEmpId': '$sequenceValue'} }" // Project the sequenceValue
    })
    Optional<Integer> findNextEmpId();

    List<Candidate> findByJobApplication_AppliedToJobIdAndJobApplication_CurrentRecruitmentStage_RecruitmentStageType(String jobId, String recruitmentStageType);


}
