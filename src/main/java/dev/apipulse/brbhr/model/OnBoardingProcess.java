package dev.apipulse.brbhr.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OnBoardingProcess {

    @Id
    private String id;
    List<OnboardingStage> onboardingStages;
    String jobPostingId;

    public void insertStage(int index, OnboardingStage stage) {
        if (index < 1) {
            throw new IllegalArgumentException("Invalid index for onboarding stage insertion");
        }
        if(index > onboardingStages.size()){
            onboardingStages.add(stage);
            return;
        }
        onboardingStages.add(index, stage);
    }

}
