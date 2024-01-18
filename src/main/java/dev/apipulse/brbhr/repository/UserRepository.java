package dev.apipulse.brbhr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yourpackage.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
