package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.Attendance;
import dev.apipulse.brbhr.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@Slf4j
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/checkin/{empId}")
    public ResponseEntity<Attendance> clockIn(@PathVariable String empId) {
        Attendance clockedInAttendance = attendanceService.checkIn(empId);
        return ResponseEntity.ok(clockedInAttendance);
    }

    @PostMapping("/addOrUpdate")
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) {
        Attendance clockedInAttendance = attendanceService.add(attendance);
        return ResponseEntity.ok(clockedInAttendance);
    }

    @PutMapping("/checkout/{attendanceId}")
    public ResponseEntity<Attendance> clockOut(@PathVariable String attendanceId) {
        Attendance clockedOutAttendance = attendanceService.checkOut(attendanceId);
        return ResponseEntity.ok(clockedOutAttendance);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployeeId(@PathVariable String employeeId) {
        List<Attendance> attendances = attendanceService.getAttendanceByEmployeeId(employeeId);
        return ResponseEntity.ok(attendances);
    }
    @PutMapping("/validate/{attendanceId}")
    public ResponseEntity<Attendance> validateAttendance(@PathVariable String attendanceId, @RequestParam String validatorId) {
        Attendance validatedAttendance = attendanceService.validateAttendance(attendanceId, validatorId);
        return ResponseEntity.ok(validatedAttendance);
    }

    @PutMapping("/invalidate/{attendanceId}")
    public ResponseEntity<Attendance> invalidateAttendance(@PathVariable String attendanceId, @RequestParam String invalidatorId) {
        Attendance invalidatedAttendance = attendanceService.invalidateAttendance(attendanceId, invalidatorId);
        return ResponseEntity.ok(invalidatedAttendance);
    }

    @GetMapping("/date/{specificDate}")
    public ResponseEntity<List<Attendance>> getAttendanceByDate(@PathVariable String specificDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(specificDate, formatter);

            List<Attendance> attendances = attendanceService.getAttendanceByDate(date);
            return ResponseEntity.ok(attendances);

        } catch (DateTimeParseException e) {
            // Handle the exception, e.g., return an error response
            // Logging the exception
            log.error("Invalid date format: " + e.getMessage());
            return ResponseEntity.badRequest().body(null); // Or use a more appropriate error response
        }
    }

    @GetMapping("/month/{year}/{month}")
    public ResponseEntity<List<Attendance>> getAttendanceByMonth(@PathVariable int year, @PathVariable int month) {
        List<Attendance> attendances = attendanceService.getAttendanceByMonth(year, month);
        return ResponseEntity.ok(attendances);
    }


}
