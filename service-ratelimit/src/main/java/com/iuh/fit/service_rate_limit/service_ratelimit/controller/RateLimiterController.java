package com.iuh.fit.service_rate_limit.service_ratelimit.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RateLimiterController {

    private final RestTemplate restTemplate;

    @GetMapping("/rl")
    @RateLimiter(name = "rlExample", fallbackMethod = "rateFallback")
    public Map<String, String> callWithRateLimiter(@RequestParam(defaultValue = "ok") String mode) {
        String url = "http://localhost:8090/process?mode=" + mode;
        String response = restTemplate.getForObject(url, String.class);

        Map<String, String> result = new HashMap<>();
        result.put("status", "success");
        result.put("mode", mode);
        result.put("message", response);
        return result;
    }

    public Map<String, String> rateFallback(String mode, Throwable t) {
        Map<String, String> result = new HashMap<>();
        result.put("status", "rate_limited");
        result.put("mode", mode);
        result.put("error", t.getMessage());
        return result;
    }
}
