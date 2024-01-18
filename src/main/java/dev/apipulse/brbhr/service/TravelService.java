package dev.apipulse.brbhr.service;

@Service
public class TravelService {

    private final TravelRepository travelRepository;

    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public List<TravelRequest> getTravelByEmployee(Long employeeId) {
        return travelRepository.findByEmployeeId(employeeId);
    }

    public TravelRequest createTravelRequest(TravelRequest travelRequest) {
        return travelRepository.save(travelRequest);
    }

    public TravelRequest updateTravelRequest(Long id, TravelRequest travelRequestDetails) {
        TravelRequest travelRequest = travelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Travel Request not found"));
        // Update logic
        return travelRepository.save(travelRequest);
    }

    public void deleteTravelRequest(Long id) {
        TravelRequest travelRequest = travelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Travel Request not found"));
        travelRepository.delete(travelRequest);
    }
}
