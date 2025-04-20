package com.vn.time_a.service;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AService {

    private final RestTemplate restTemplate;

    public AService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @TimeLimiter(name = "serviceB", fallbackMethod = "fallback")
    public CompletableFuture<String> callB() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Calling B...");
            return restTemplate.getForObject("http://localhost:8081/b", String.class);
        });
    }

    public CompletableFuture<String> fallback(Throwable ex) {
        return CompletableFuture.completedFuture(" Timeout! B too slow → fallback.");
    }
}

