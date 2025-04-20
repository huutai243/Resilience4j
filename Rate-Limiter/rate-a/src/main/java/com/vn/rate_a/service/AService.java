package com.vn.rate_a.service;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AService {
    private final RestTemplate restTemplate;

    public AService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RateLimiter(name = "serviceB", fallbackMethod = "rateFallback")
    public String callB() {
        System.out.println(" Calling B...");
        return restTemplate.getForObject("http://localhost:8081/b", String.class);
    }

    public String rateFallback(Throwable ex) {
        return " Rate limit exceeded. Please try later.";
    }
}


