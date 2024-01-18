package dev.apipulse.brbhr.controller;

@RestController
@RequestMapping("/api/diversity")
public class DiversityController {

    private final DiversityService diversityService;

    public DiversityController(DiversityService diversityService) {
        this.diversityService = diversityService;
    }

    @GetMapping("/metrics")
    public ResponseEntity<DiversityMetrics> getDiversityMetrics() {
        DiversityMetrics metrics = diversityService.getDiversityMetrics();
        return ResponseEntity.ok(metrics);
    }

    @PostMapping("/feedback")
    public ResponseEntity<Void> submitDiversityFeedback(@RequestBody DiversityFeedback feedback) {
        diversityService.submitFeedback(feedback);
        return ResponseEntity.noContent().build();
    }
}
