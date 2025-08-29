package com.bajaj.bajaj_task;



import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class GenerateWebhookRunner implements CommandLineRunner {

    private final RestTemplate restTemplate;

    public GenerateWebhookRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        Map<String, String> body = new HashMap<>();
        body.put("name", "Samriddhi Tripathi");   
        body.put("regNo", "22BCY10041");  
        body.put("email", "samriddhitripathi2022@vitbhopal.ac.in");  

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<WebhookResponse> response =
                restTemplate.postForEntity(url, request, WebhookResponse.class);

        WebhookResponse webhookResponse = response.getBody();
        System.out.println("Got webhook: " + webhookResponse.getWebhook());
        System.out.println("Got accessToken: " + webhookResponse.getAccessToken());
    }
}
