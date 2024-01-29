package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.OKR;
import dev.apipulse.brbhr.model.PerformanceReview;
import dev.apipulse.brbhr.model.QuestionTemplate;
import dev.apipulse.brbhr.repository.PerformanceReviewRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {

    private final PerformanceReviewRepository reviewRepository;

    public PerformanceService(PerformanceReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<PerformanceReview> getPerformanceReviews(String employeeId) {
        return reviewRepository.findByEmployeeId(employeeId);
    }

    public PerformanceReview createPerformanceReview(PerformanceReview review) {
        // Logic to create a performance review
        return reviewRepository.save(review);
    }

    public PerformanceReview updatePerformanceReview(String id, PerformanceReview reviewDetails) {
        PerformanceReview review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Performance Review not found"));
        // Update logic
        return reviewRepository.save(review);
    }

    public void createOKR(OKR okr) {
    }

    public void createQuestionTemplate(QuestionTemplate questionTemplate) {
    }
}
