package dev.apipulse.brbhr.controller;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<PerformanceReview>> getPerformanceReviews(@PathVariable Long employeeId) {
        List<PerformanceReview> reviews = performanceService.getPerformanceReviews(employeeId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<PerformanceReview> createPerformanceReview(@RequestBody PerformanceReview review) {
        PerformanceReview createdReview = performanceService.createPerformanceReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceReview> updatePerformanceReview(@PathVariable Long id, @RequestBody PerformanceReview review) {
        PerformanceReview updatedReview = performanceService.updatePerformanceReview(id, review);
        return ResponseEntity.ok(updatedReview);
    }
}
