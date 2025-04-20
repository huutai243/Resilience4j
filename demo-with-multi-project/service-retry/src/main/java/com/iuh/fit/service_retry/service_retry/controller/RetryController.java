package com.iuh.fit.service_retry.service_retry.controller;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class RetryController {

    private final RestTemplate restTemplate;

    @GetMapping("/retry")
    @Retry(name = "retryExample", fallbackMethod = "retryFallback")
    public String callWithRetry() {
        String response = restTemplate.getForObject("http://localhost:8090/fail", String.class);
        return "Retry Success: " + response;
    }

    public String retryFallback(Throwable t) {
        return "Fallback after retries: " + t.getMessage();
    }
}
