package dev.apipulse.brbhr.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ChatController {
    private final ApiService apiService;
    @Autowired
    ApiResponseUserChatRepository apiResponseUserChatRepository;

    public ChatController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ApiResponse> makeChat(@RequestBody UserChatRequest userChatRequest) {
        if(userChatRequest.getChatMessageList().size()>20){
            return ResponseEntity.internalServerError().build();
        }
        ChatRequest chatRequest = new ChatRequest();
        String currentTime = userChatRequest.getUserCurrentTime();
        ChatMessagePrompt prompt = userChatRequest.getIsNight() ? ChatMessagePrompt.VULGAR_GIRLFRIEND : ChatMessagePrompt.MELONI;
        userChatRequest.getChatMessageList().add(0,new ChatMessage(ChatMessagePrompt.MELONI.getType(), ChatMessagePrompt.MELONI.getMessage()));
        userChatRequest.getChatMessageList().add(1,new ChatMessage(prompt.getType(), prompt.getMessage()));
        chatRequest.setMessages(userChatRequest.getChatMessageList());
        ApiResponse response = apiService.callExternalApi(chatRequest);
        if(userChatRequest.getMessageNo() == 20) {
            ApiResponseUserChat apiResponseUserChat = new ApiResponseUserChat();
            apiResponseUserChat.setUserChatRequest(userChatRequest);
            apiResponseUserChat.setApiResponse(response);
            apiResponseUserChatRepository.save(apiResponseUserChat);
        }
        return ResponseEntity.ok(response);
    }

    public boolean isAfter10PM(String currentDateTime) {
        // Define both formatters
        DateTimeFormatter formatter24Hour = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");
        DateTimeFormatter formatterAmPm = DateTimeFormatter.ofPattern("M/d/uuuu, h:mm:ss a");
        currentDateTime = currentDateTime.replace('\u202F', ' ').trim();
        // Attempt to parse the input with the 24-hour format
        LocalDateTime userDateTime;
        try {
            userDateTime = LocalDateTime.parse(currentDateTime, formatter24Hour);
        } catch (DateTimeParseException e) {
            // If parsing fails, attempt to parse with the am/pm format
            try {
                userDateTime = LocalDateTime.parse(currentDateTime, formatterAmPm);
            } catch (DateTimeParseException ex) {
                // If both parsing attempts fail, throw an exception or handle the error appropriately
                //throw new IllegalArgumentException("Input datetime format is incorrect or unsupported.",ex);
                return false;
            }
        }
        LocalDateTime tenPMOnSameDay = userDateTime.toLocalDate().atTime(22, 0);
        LocalDateTime fourAMOnSameDay = userDateTime.toLocalDate().atTime(4, 0);
        return userDateTime.isAfter(tenPMOnSameDay) || userDateTime.isBefore(fourAMOnSameDay);
    }
    public static void main(String args[]){
        //boolean d = isAfter10PM("8/2/2024, 1:04:24 pm");
       // System.out.println(d);
    }
}
