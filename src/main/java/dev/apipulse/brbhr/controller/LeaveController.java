package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.Leave;
import dev.apipulse.brbhr.service.LeaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Leave>> getLeavesByEmployee(@PathVariable Long employeeId) {
        List<Leave> leaves = leaveService.getLeavesByEmployee(employeeId);
        return ResponseEntity.ok(leaves);
    }

    @PostMapping
    public ResponseEntity<Leave> applyForLeave(@RequestBody Leave leave) {
        Leave appliedLeave = leaveService.applyForLeave(leave);
        return new ResponseEntity<>(appliedLeave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leave> updateLeave(@PathVariable Long id, @RequestBody Leave leave) {
        Leave updatedLeave = leaveService.updateLeave(id, leave);
        return ResponseEntity.ok(updatedLeave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }
}
