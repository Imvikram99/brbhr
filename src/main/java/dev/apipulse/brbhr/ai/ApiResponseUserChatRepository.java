package dev.apipulse.brbhr.ai;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiResponseUserChatRepository extends MongoRepository<ApiResponseUserChat, String> {

}