package dev.apipulse.brbhr.controller;

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
    public ResponseEntity<List<Notification>> getNotificationsByEmployee(@PathVariable Long employeeId) {
        List<Notification> notifications = notificationService.getNotificationsByEmployee(employeeId);
        return ResponseEntity.ok(notifications);
    }
}
