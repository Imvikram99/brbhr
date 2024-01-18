package dev.apipulse.brbhr.service;

@Service
public class DiversityService {

    private final DiversityMetricsRepository diversityMetricsRepository;

    public DiversityService(DiversityMetricsRepository diversityMetricsRepository) {
        this.diversityMetricsRepository = diversityMetricsRepository;
    }

    public DiversityMetrics getDiversityMetrics() {
        // Logic to calculate and return diversity metrics
        return diversityMetricsRepository.findLatestMetrics();
    }

    public void submitFeedback(DiversityFeedback feedback) {
        // Logic to handle diversity feedback submission
        // This could involve saving the feedback and triggering any follow-up actions
    }
}
