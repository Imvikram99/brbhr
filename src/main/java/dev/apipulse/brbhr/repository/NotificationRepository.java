package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByEmployeeId(String employeeId);
}
