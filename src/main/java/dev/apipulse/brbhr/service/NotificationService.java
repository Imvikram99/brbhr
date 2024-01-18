package dev.apipulse.brbhr.service;

import dev.apipulse.brbhr.model.Notification;
import dev.apipulse.brbhr.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Notification> getNotificationsByEmployee(String employeeId) {
        // Retrieve notifications for an employee
        return notificationRepository.findByEmployeeId(employeeId);
    }
}
