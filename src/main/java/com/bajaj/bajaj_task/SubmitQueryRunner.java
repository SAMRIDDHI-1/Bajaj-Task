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
        // 👉 NOTE:
        // Ye values abhi manually daal raha ho
        // tu GenerateWebhookRunner se copy karke paste kar de
        String webhookUrl = "PASTE_YOUR_WEBHOOK_HERE";
        String accessToken = "PASTE_YOUR_TOKEN_HERE";

        // Ab yahan apna SQL query daal (jo question ke hisaab se solve karni hai)
       String sqlQuery = "SELECT column1, column2 FROM your_table WHERE condition1 = 'value' ORDER BY column1;";
// 👈 apni query daalni hai

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken); // JWT token lag gaya

        Map<String, String> body = new HashMap<>();
        body.put("finalQuery", sqlQuery);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(webhookUrl, request, String.class);

        System.out.println("Submit response: " + response.getBody());
    }
}
