package dev.apipulse.brbhr.service;

@Service
public class SalaryAdvanceService {

    private final SalaryAdvanceRepository salaryAdvanceRepository;

    public SalaryAdvanceService(SalaryAdvanceRepository salaryAdvanceRepository) {
        this.salaryAdvanceRepository = salaryAdvanceRepository;
    }

    public List<SalaryAdvance> getSalaryAdvancesByEmployee(Long employeeId) {
        return salaryAdvanceRepository.findByEmployeeId(employeeId);
    }

    public SalaryAdvance requestSalaryAdvance(SalaryAdvance salaryAdvance) {
        return salaryAdvanceRepository.save(salaryAdvance);
    }

    public SalaryAdvance updateSalaryAdvance(Long id, SalaryAdvance salaryAdvanceDetails) {
        SalaryAdvance advance = salaryAdvanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salary Advance not found"));
        // Update logic
        return salaryAdvanceRepository.save(advance);
    }
}
