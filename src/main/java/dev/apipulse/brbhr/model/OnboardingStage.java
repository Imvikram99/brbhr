package dev.apipulse.brbhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingStage {
    @Id
    private String id;
    private String name;
    private String description;
    private String defaultManager;
    private Map<String,String> candidateIdVsManager;

}
