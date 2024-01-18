package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EventRepository extends MongoRepository<Event, String> {
    // Additional query methods if required
}
