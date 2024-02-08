package dev.apipulse.brbhr.ai;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChatRequest {
    List<ChatMessage> chatMessageList;
    String userId;
    String emailId;
    String mobileNo;
    Integer messageNo;
    Double latitude;
    Double longitude;
    String userCurrentTime;
    Boolean isNight;
}
