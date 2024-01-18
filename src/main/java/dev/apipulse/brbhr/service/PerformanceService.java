package dev.apipulse.brbhr.service;

@Service
public class PerformanceService {

    private final PerformanceReviewRepository reviewRepository;

    public PerformanceService(PerformanceReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<PerformanceReview> getPerformanceReviews(Long employeeId) {
        return reviewRepository.findByEmployeeId(employeeId);
    }

    public PerformanceReview createPerformanceReview(PerformanceReview review) {
        // Logic to create a performance review
        return reviewRepository.save(review);
    }

    public PerformanceReview updatePerformanceReview(Long id, PerformanceReview reviewDetails) {
        PerformanceReview review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Performance Review not found"));
        // Update logic
        return reviewRepository.save(review);
    }
}
