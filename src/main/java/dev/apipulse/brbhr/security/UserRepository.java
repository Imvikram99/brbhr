package dev.apipulse.brbhr.security;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Log4j2
public class UserRepository {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    public Optional<User> findByUsername(String username) {
        log.info("Fetching user details for username: " + username );
        MongoClient defaultClient = MongoClients.create(mongoUri);
        MongoDatabase database = defaultClient.getDatabase("brbhr");
        MongoCollection<Document> collection = database.getCollection("userProfile");

        Document document = collection.find(Filters.eq("username", username)).first();

        if (document != null) {
            User user = new User(); // Assuming you have a suitable User class
            // Map fields from document to user
            user.setId(document.getString("id"));
            user.setPassword(document.getString("password"));
            user.setUsername(document.getString("username"));
            List<String> rolesList = document.getList("roles", String.class);
            Set<Role> roles = rolesList.stream()
                    .map(Role::valueOf) // assuming Role is an enum and string in DB corresponds to the enum value
                    .collect(Collectors.toSet());
            user.setRoles(roles);

            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
