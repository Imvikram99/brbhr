package dev.apipulse.brbhr.controller;

import dev.apipulse.brbhr.model.Notification;
import dev.apipulse.brbhr.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Notification> sendNotification(@RequestBody Notification notification) {
        Notification sentNotification = notificationService.sendNotification(notification);
        return new ResponseEntity<>(sentNotification, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Notification>> getNotificationsByEmployee(@PathVariable String employeeId) {
        List<Notification> notifications = notificationService.getNotificationsByEmployee(employeeId);
        return ResponseEntity.ok(notifications);
    }
}
