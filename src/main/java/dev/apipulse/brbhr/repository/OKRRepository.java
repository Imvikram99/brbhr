package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.OKR;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OKRRepository extends MongoRepository<OKR, String> {
}
