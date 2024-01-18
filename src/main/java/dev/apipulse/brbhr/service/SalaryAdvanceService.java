package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.SalaryAdvance;
import dev.apipulse.brbhr.repository.SalaryAdvanceRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryAdvanceService {

    private final SalaryAdvanceRepository salaryAdvanceRepository;

    public SalaryAdvanceService(SalaryAdvanceRepository salaryAdvanceRepository) {
        this.salaryAdvanceRepository = salaryAdvanceRepository;
    }

    public List<SalaryAdvance> getSalaryAdvancesByEmployee(String employeeId) {
        return salaryAdvanceRepository.findByEmployeeId(employeeId);
    }

    public SalaryAdvance requestSalaryAdvance(SalaryAdvance salaryAdvance) {
        return salaryAdvanceRepository.save(salaryAdvance);
    }

    public SalaryAdvance updateSalaryAdvance(String id, SalaryAdvance salaryAdvanceDetails) {
        SalaryAdvance advance = salaryAdvanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salary Advance not found"));
        // Update logic
        return salaryAdvanceRepository.save(advance);
    }
}
