package dev.apipulse.brbhr.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${api.bearer.token}")
    private String bearerToken;
    @Value("${api.llm.url}")
    private String llmUrl;

    @Value("${api.llm.model}")
    private String llmModel;


    public ApiResponse callExternalApi(ChatRequest request) {
        request.setModel(llmModel);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(bearerToken);
        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(llmUrl, entity, ApiResponse.class);
    }
    public void saveResponse(){

    }
}
