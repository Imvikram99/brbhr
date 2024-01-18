package dev.apipulse.brbhr.service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification sendNotification(Notification notification) {
        // Business logic to send notification
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByEmployee(Long employeeId) {
        // Retrieve notifications for an employee
        return notificationRepository.findByEmployeeId(employeeId);
    }
}
