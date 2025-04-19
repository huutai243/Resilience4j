package com.iuh.fit.gateway_client.gatewayclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/call")
@RequiredArgsConstructor
public class GatewayClientController {

    private final RestTemplate restTemplate;

    @GetMapping("/retry")
    public String callRetryService(@RequestParam(defaultValue = "ok") String mode) {
        return restTemplate.getForObject("http://localhost:8081/retry?mode=" + mode, String.class);
    }

    @GetMapping("/cb")
    public String callCircuitBreakerService(@RequestParam(defaultValue = "ok") String mode) {
        return restTemplate.getForObject("http://localhost:8082/cb?mode=" + mode, String.class);
    }

    @GetMapping("/rl")
    public String callRateLimiterService(@RequestParam(defaultValue = "ok") String mode) {
        return restTemplate.getForObject("http://localhost:8083/rl?mode=" + mode, String.class);
    }

    @GetMapping("/time")
    public String callTimeLimiterService(@RequestParam(defaultValue = "ok") String mode) {
        return restTemplate.getForObject("http://localhost:8084/time?mode=" + mode, String.class);
    }
}
