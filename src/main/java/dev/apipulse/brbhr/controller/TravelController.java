package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.TravelRequest;
import dev.apipulse.brbhr.service.TravelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<TravelRequest>> getTravelByEmployee(@PathVariable String employeeId) {
        List<TravelRequest> travelRequests = travelService.getTravelByEmployee(employeeId);
        return ResponseEntity.ok(travelRequests);
    }

    @PostMapping
    public ResponseEntity<TravelRequest> createTravelRequest(@RequestBody TravelRequest travelRequest) {
        TravelRequest createdRequest = travelService.createTravelRequest(travelRequest);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelRequest> updateTravelRequest(@PathVariable String id, @RequestBody TravelRequest travelRequest) {
        TravelRequest updatedRequest = travelService.updateTravelRequest(id, travelRequest);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravelRequest(@PathVariable String id) {
        travelService.deleteTravelRequest(id);
        return ResponseEntity.noContent().build();
    }
}
