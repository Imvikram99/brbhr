package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.Event;

public interface EventRepository extends MongoRepository<Event, String> {
    // Additional query methods if required
}
