package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.Leave;
import dev.apipulse.brbhr.repository.LeaveRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public List<Leave> getLeavesByEmployee(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId);
    }

    public Leave applyForLeave(Leave leave) {
        // Logic for applying for leave
        return leaveRepository.save(leave);
    }

    public Leave updateLeave(Long id, Leave leaveDetails) {
        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        // Update logic
        return leaveRepository.save(leave);
    }

    public void deleteLeave(Long id) {
        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        leaveRepository.delete(leave);
    }
}
