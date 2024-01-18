package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.Payroll;
import dev.apipulse.brbhr.repository.PayrollRepository;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    public Payroll getPayrollByEmployee(String employeeId) {
        // Logic to retrieve payroll details
        return payrollRepository.findByEmployeeId(employeeId);
    }

    public Payroll processPayroll(Payroll payroll) {
        // Logic to process payroll
        return payrollRepository.save(payroll);
    }

    public Payroll updatePayroll(String employeeId, Payroll payrollDetails) {
        // Logic to update payroll information
        Payroll payroll = payrollRepository.findByEmployeeId(employeeId);
        // Update logic
        return payrollRepository.save(payroll);
    }
}
