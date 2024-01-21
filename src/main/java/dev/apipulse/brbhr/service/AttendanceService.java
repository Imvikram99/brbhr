package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.Attendance;
import dev.apipulse.brbhr.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance checkIn(String employeeId) {
        // Create a new Attendance record with the current time as checkInTime
        Attendance newAttendance = new Attendance();
        newAttendance.setEmployeeId(employeeId);
        newAttendance.setCheckInTime(String.valueOf(LocalDateTime.now()));
        // Set other fields as necessary
        return attendanceRepository.save(newAttendance);
    }

    public Attendance add(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public Attendance checkOut(String attendanceId) {
        // Retrieve the existing Attendance record and update checkOutTime
        Attendance existingAttendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        existingAttendance.setCheckOutTime(String.valueOf(LocalDateTime.now()));
        return attendanceRepository.save(existingAttendance);
    }

    public List<Attendance> getAttendanceByEmployeeId(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    public List<Attendance> getAttendanceByMonth(int year, int month) {
        LocalDate startOfMonth = YearMonth.of(year, month).atDay(1);
        LocalDate endOfMonth = YearMonth.of(year, month).atEndOfMonth();
        return attendanceRepository.findByDateBetween(startOfMonth, endOfMonth);
    }

    public Attendance validateAttendance(String attendanceId, String validatorId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        attendance.setIsValidated(true);
        attendance.setValidatorId(validatorId);
        return attendanceRepository.save(attendance);
    }

    public Attendance invalidateAttendance(String attendanceId, String invalidatorId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        attendance.setIsValidated(false);
        attendance.setValidatorId(invalidatorId);
        return attendanceRepository.save(attendance);
    }
}
