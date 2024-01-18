package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import dev.apipulse.brbhr.model.Offer;

public interface OfferRepository extends MongoRepository<Offer, String> {
    // Query methods for managing job offers
}
