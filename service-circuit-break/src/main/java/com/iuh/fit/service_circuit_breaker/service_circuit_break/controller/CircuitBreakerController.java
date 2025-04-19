package com.iuh.fit.service_circuit_breaker.service_circuit_break.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cb")
public class CircuitBreakerController {

    private final RestTemplate restTemplate;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    public CircuitBreakerController(RestTemplate restTemplate, CircuitBreakerRegistry registry) {
        this.restTemplate = restTemplate;
        this.circuitBreakerRegistry = registry;
    }

    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "cbExample", fallbackMethod = "cbFallback")
    @GetMapping
    public String callDownstream() {
        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("cbExample");
        String response = restTemplate.getForObject("http://localhost:8090/process", String.class);
        return "[STATE: " + cb.getState() + "] ✅ Response: " + response;
    }

    public String cbFallback(Throwable t) {
        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("cbExample");
        return "[STATE: " + cb.getState() + "] ❌ Fallback triggered: " + t.getMessage();
    }
}
