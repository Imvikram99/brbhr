package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.WellnessActivity;
import dev.apipulse.brbhr.model.WellnessProgram;
import dev.apipulse.brbhr.service.WellnessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wellness")
public class WellnessController {

    private final WellnessService wellnessService;

    public WellnessController(WellnessService wellnessService) {
        this.wellnessService = wellnessService;
    }

    @GetMapping("/activities/{employeeId}")
    public ResponseEntity<List<WellnessActivity>> getActivitiesByEmployee(@PathVariable String employeeId) {
        List<WellnessActivity> activities = wellnessService.getActivitiesByEmployee(employeeId);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/activities")
    public ResponseEntity<WellnessActivity> logActivity(@RequestBody WellnessActivity activity) {
        WellnessActivity loggedActivity = wellnessService.logActivity(activity);
        return new ResponseEntity<>(loggedActivity, HttpStatus.CREATED);
    }

    @GetMapping("/programs")
    public ResponseEntity<List<WellnessProgram>> getAllWellnessPrograms() {
        List<WellnessProgram> programs = wellnessService.getAllWellnessPrograms();
        return ResponseEntity.ok(programs);
    }
}
