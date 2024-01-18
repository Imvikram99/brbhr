package dev.apipulse.brbhr.controller;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<TravelRequest>> getTravelByEmployee(@PathVariable Long employeeId) {
        List<TravelRequest> travelRequests = travelService.getTravelByEmployee(employeeId);
        return ResponseEntity.ok(travelRequests);
    }

    @PostMapping
    public ResponseEntity<TravelRequest> createTravelRequest(@RequestBody TravelRequest travelRequest) {
        TravelRequest createdRequest = travelService.createTravelRequest(travelRequest);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelRequest> updateTravelRequest(@PathVariable Long id, @RequestBody TravelRequest travelRequest) {
        TravelRequest updatedRequest = travelService.updateTravelRequest(id, travelRequest);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravelRequest(@PathVariable Long id) {
        travelService.deleteTravelRequest(id);
        return ResponseEntity.noContent().build();
    }
}
