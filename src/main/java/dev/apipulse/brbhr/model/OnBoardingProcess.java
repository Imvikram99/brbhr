package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnBoardingProcess {

    @Id
    private String id;
    List<OnboardingStage> onboardingStages;
    String jobPostingId;

    public void insertStage(int index, OnboardingStage stage) {
        if (index < 1 || index > onboardingStages.size()) {
            throw new IllegalArgumentException("Invalid index for onboarding stage insertion");
        }
        onboardingStages.add(index, stage);
    }

}
