package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.Grievance;
import dev.apipulse.brbhr.repository.GrievanceRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrievanceService {

    private final GrievanceRepository grievanceRepository;

    public GrievanceService(GrievanceRepository grievanceRepository) {
        this.grievanceRepository = grievanceRepository;
    }

    public Grievance fileGrievance(Grievance grievance) {
        return grievanceRepository.save(grievance);
    }

    public List<Grievance> getGrievancesByEmployee(Long employeeId) {
        return grievanceRepository.findByEmployeeId(employeeId);
    }

    public Grievance updateGrievance(Long id, Grievance grievanceDetails) {
        Grievance grievance = grievanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grievance not found"));
        // Update logic
        return grievanceRepository.save(grievance);
    }
}
