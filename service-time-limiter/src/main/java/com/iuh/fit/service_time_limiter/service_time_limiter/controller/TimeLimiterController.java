package com.iuh.fit.service_time_limiter.service_time_limiter.controller;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class TimeLimiterController {

    private final RestTemplate restTemplate;

    @GetMapping("/time")
    @TimeLimiter(name = "timeLimiterExample", fallbackMethod = "timeLimiterFallback")
    public CompletableFuture<String> callWithTimeout() {
        return CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://localhost:8090/slow", String.class)
        );
    }

    public CompletableFuture<String> timeLimiterFallback(Throwable t) {
        return CompletableFuture.completedFuture("Timeout fallback: " + t.getMessage());
    }
}
