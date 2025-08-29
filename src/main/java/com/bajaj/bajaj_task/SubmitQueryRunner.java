package com.bajaj.bajaj_task;



import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class SubmitQueryRunner implements CommandLineRunner {

    private final RestTemplate restTemplate;

    public SubmitQueryRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
     
        String webhookUrl = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA ";
        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJyZWdObyI6IjIyQkNZMTAwNDEiLCJuYW1lIjoiU2FtcmlkZGhpIFRyaXBhdGhpIiwiZW1haWwiOiJzYW1yaWRkaGl0cmlwYXRoaTIwMjJAdml0YmhvcGFsLmFjLmluIiwic3ViIjoid2ViaG9vay11c2VyIiwiaWF0IjoxNzU2NDQ5NDQ3LCJleHAiOjE3NTY0NTAzNDd9.-qmLLgYZxEqyvQenCJPNd246kUe_7VxYR30fZ4JjoYo";

       String sqlQuery = "SELECT column1, column2 FROM your_table WHERE condition1 = 'value' ORDER BY column1;";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken); 

        Map<String, String> body = new HashMap<>();
        body.put("finalQuery", sqlQuery);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(webhookUrl, request, String.class);

        System.out.println("Submit response: " + response.getBody());
    }
}
