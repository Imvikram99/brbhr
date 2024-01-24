package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.OnBoardingProcess;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import dev.apipulse.brbhr.model.Candidate;
import java.util.List;

@Repository
public interface OnboardingRepository extends MongoRepository<OnBoardingProcess, String> {

    @Query("{ 'jobPostingId' : ?0 }")
    OnBoardingProcess findOnboardingProcessByJobId(String appliedToJobId);
}
