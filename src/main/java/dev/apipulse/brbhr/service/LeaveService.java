package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.*;
import dev.apipulse.brbhr.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    LeaveDetailsRepository leaveDetailsRepository;

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Autowired
    HolidaysRepository holidaysRepository;

    @Autowired
    CompanyLeaveRepository companyLeaveRepository;



    // Method to get leaves by employee ID
    public List<Leave> getLeavesByEmployee(String employeeId) {
        return leaveRepository.findByEmployeeId(employeeId);
    }


    // Method to get all leave types
    public List<LeaveTypeDetail> getLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    // Method to get assigned leaves for an employee
    public List<Leave> getAssignedLeaves(String assignedToEmpId) {
        return leaveRepository.findByAssignedToEmpId(assignedToEmpId);
    }

    // Method to get all leave requests
    public List<Leave> getAllLeaveRequests() {
        return leaveRepository.findAll();
    }


    public LeaveDetails getMyLeaves(String employeeId) {
        return leaveDetailsRepository.findByEmpId(employeeId);
    }

    public LeaveTypeDetail addLeaveType(LeaveTypeDetail leaveTypeDetail) {
        return leaveTypeRepository.save(leaveTypeDetail);
    }

    public CompanyLeave createCompanyLeave(CompanyLeave companyLeave) {
        return companyLeaveRepository.save(companyLeave);
    }

    public CompanyLeave getCompanyLeaveById(String id) {
        return companyLeaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CompanyLeave not found with id: " + id));
    }

    public CompanyLeave updateCompanyLeave(String id, CompanyLeave companyLeave) {
        return companyLeaveRepository.findById(id)
                .map(existingLeave -> {
                     existingLeave.setBasedOnWeek(companyLeave.getBasedOnWeek());
                     existingLeave.setBasedOnWeekDay(companyLeave.getBasedOnWeekDay());
                    return companyLeaveRepository.save(existingLeave);
                })
                .orElseThrow(() -> new RuntimeException("CompanyLeave not found with id: " + id));
    }

    public void deleteCompanyLeave(String id) {
        companyLeaveRepository.deleteById(id);
    }

    public Holidays createHoliday(Holidays holiday) {
        return holidaysRepository.save(holiday);
    }

    public Holidays getHolidayById(String id) {
        return holidaysRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found with id: " + id));
    }

    public Holidays updateHoliday(String id, Holidays holiday) {
        return holidaysRepository.findById(id)
                .map(existingHoliday -> {
                    // Update fields of the existing entity here
                    // For example:
                    // existingHoliday.setHolidayName(holiday.getHolidayName());
                    // existingHoliday.setStartDate(holiday.getStartDate());
                    // existingHoliday.setEndDate(holiday.getEndDate());
                    // existingHoliday.setRecurring(holiday.getRecurring());
                    // Add other fields as necessary
                    return holidaysRepository.save(existingHoliday);
                })
                .orElseThrow(() -> new RuntimeException("Holiday not found with id: " + id));
    }

    public void deleteHoliday(String id) {
        holidaysRepository.deleteById(id);
    }

    public List<CompanyLeave> getCompanyLeaves() {
        return companyLeaveRepository.findAll();
    }

    public List<Holidays> getHolidays() {
        return holidaysRepository.findAll();
    }

    public LeaveDetails applyLeaves(String employeeId, LeaveRequest leaveRequest) {
        LeaveDetails leaveDetails = leaveDetailsRepository.findByEmpIdAndType(employeeId, leaveRequest.getType());
        Integer leavesAppliedForDays = leaveRequest.getNoOfDays();
        if (leaveDetails != null && leaveDetails.getAvailableLeaves() >= leavesAppliedForDays) {
            // Deduct the requested number of days from the leave balance.
            leaveDetails.setLeavesTaken(leaveDetails.getLeavesTaken() + leavesAppliedForDays);
            leaveDetails.setAvailableLeaves(leaveDetails.getAvailableLeaves() - leavesAppliedForDays);
            // Save the updated leave details back to the repository.
            leaveDetailsRepository.save(leaveDetails);
        }
        return leaveDetails;
    }

    public void updateLeaveStatus(String leaveId, String status, String reason) {
        Optional<Leave> leaveOptional = leaveRepository.findById(leaveId);

        if (!leaveOptional.isPresent()) {
            // Handle the case where the leave does not exist
            throw new IllegalArgumentException("Leave with ID " + leaveId + " does not exist.");
        }

        Leave leave = leaveOptional.get();
        leave.setStatus(status);
        leave.setApproverReason(reason);
        leaveRepository.save(leave);
    }
}
