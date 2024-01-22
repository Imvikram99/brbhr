package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.Leave;
import dev.apipulse.brbhr.model.LeaveDetails;
import dev.apipulse.brbhr.model.*;
import dev.apipulse.brbhr.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    @GetMapping("/myleaves/{employeeId}")
    public ResponseEntity<LeaveDetails> getMyLeaves(@PathVariable String employeeId) {
        return ResponseEntity.ok(leaveService.getMyLeaves(employeeId));
    }

    @GetMapping("/myleaves-requests/{employeeId}")
    public ResponseEntity<List<Leave>> getMyLeaveRequests(@PathVariable String employeeId) {
        return ResponseEntity.ok(leaveService.getLeavesByEmployee(employeeId));
    }

    @GetMapping("/leave-types")
    public ResponseEntity<List<LeaveTypeDetail>> getLeaveTypes() {
        return ResponseEntity.ok(leaveService.getLeaveTypes());
    }

    @PostMapping("/leave-types")
    public ResponseEntity<LeaveTypeDetail> addLeaveType(@RequestBody LeaveTypeDetail leaveTypeDetail) {
        LeaveTypeDetail createdLeaveType = leaveService.addLeaveType(leaveTypeDetail);
        return new ResponseEntity<>(createdLeaveType, HttpStatus.CREATED);
    }

    @GetMapping("/assigned-leaves/{assignedToEmpId}")
    public ResponseEntity<List<Leave>> getAssignedLeaves(@PathVariable String assignedToEmpId) {
        return ResponseEntity.ok(leaveService.getAssignedLeaves(assignedToEmpId));
    }

    @GetMapping("/leave-requests")
    public ResponseEntity<List<Leave>> getAllLeaveRequests() {
        return ResponseEntity.ok(leaveService.getAllLeaveRequests());
    }

    @PostMapping("/company-leaves")
    public ResponseEntity<CompanyLeave> createCompanyLeave(@RequestBody CompanyLeave companyLeave) {
        CompanyLeave created = leaveService.createCompanyLeave(companyLeave);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/company-leaves/{id}")
    public ResponseEntity<CompanyLeave> getCompanyLeaveById(@PathVariable String id) {
        CompanyLeave found = leaveService.getCompanyLeaveById(id);
        return ResponseEntity.ok(found);
    }

    @GetMapping("/company-leaves")
    public ResponseEntity<List<CompanyLeave>> getCompanyLeaves() {
        return ResponseEntity.ok(leaveService.getCompanyLeaves());
    }

    @PutMapping("/company-leaves/{id}")
    public ResponseEntity<CompanyLeave> updateCompanyLeave(@PathVariable String id, @RequestBody CompanyLeave companyLeave) {
        CompanyLeave updated = leaveService.updateCompanyLeave(id, companyLeave);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/company-leaves/{id}")
    public ResponseEntity<Void> deleteCompanyLeave(@PathVariable String id) {
        leaveService.deleteCompanyLeave(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/holidays")
    public ResponseEntity<Holidays> createHoliday(@RequestBody Holidays holiday) {
        Holidays created = leaveService.createHoliday(holiday);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/holidays/{id}")
    public ResponseEntity<Holidays> getHolidayById(@PathVariable String id) {
        Holidays found = leaveService.getHolidayById(id);
        return ResponseEntity.ok(found);
    }

    @GetMapping("/holidays")
    public ResponseEntity<List<Holidays>> getHolidays() {
        return ResponseEntity.ok(leaveService.getHolidays());
    }

    @PutMapping("/holidays/{id}")
    public ResponseEntity<Holidays> updateHoliday(@PathVariable String id, @RequestBody Holidays holiday) {
        Holidays updated = leaveService.updateHoliday(id, holiday);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/holidays/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable String id) {
        leaveService.deleteHoliday(id);
        return ResponseEntity.noContent().build();
    }


}
