package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByEmployeeId(String employeeId);
}
