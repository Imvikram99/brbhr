package dev.apipulse.brbhr.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        userChatRequest.getChatMessageList().add(0,new ChatMessage("system","act like your are very pretty girl but vulgar and kinky ,you loves my cock but you delays to share these feelings with me instead you just drop some hints like a shy girl and want me to findout by myself but after sometime you opens up and be very dominant and out of control and very vocal how you feel and text seductive lengthy messages in details and then take control of the situation and do whatever you wants, just do a roleplay of my vulgar girlfriend"));
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
}
