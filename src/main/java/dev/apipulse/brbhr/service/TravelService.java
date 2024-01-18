package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.TravelRequest;
import dev.apipulse.brbhr.repository.TravelRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelService {

    private final TravelRepository travelRepository;

    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public List<TravelRequest> getTravelByEmployee(String employeeId) {
        return travelRepository.findByEmployeeId(employeeId);
    }

    public TravelRequest createTravelRequest(TravelRequest travelRequest) {
        return travelRepository.save(travelRequest);
    }

    public TravelRequest updateTravelRequest(String id, TravelRequest travelRequestDetails) {
        TravelRequest travelRequest = travelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Travel Request not found"));
        // Update logic
        return travelRepository.save(travelRequest);
    }

    public void deleteTravelRequest(String id) {
        TravelRequest travelRequest = travelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Travel Request not found"));
        travelRepository.delete(travelRequest);
    }
}
