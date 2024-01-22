package dev.apipulse.brbhr.repository;

import dev.apipulse.brbhr.model.Holidays;
import dev.apipulse.brbhr.model.LeaveDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysRepository extends MongoRepository<Holidays, String> {

}
