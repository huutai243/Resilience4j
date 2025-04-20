package com.vn.retry_a.service;


import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Retryable(
            maxAttempts = 5,
            backoff = @Backoff(delay = 1000),
            include = { Exception.class }
    )
    public String callB() {
        System.out.println("Trying to call B...");
        return restTemplate.getForObject("http://localhost:8081/b", String.class);
    }
}

