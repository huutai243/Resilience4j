package com.vn.service_a.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AService {
    private final RestTemplate rt;

    public AService(RestTemplate rt) {
        this.rt = rt;
    }

    @CircuitBreaker(name = "serviceB", fallbackMethod = "fallback")
    public String callB(boolean fail) {
        return rt.getForObject("http://localhost:8081/b?fail=" + fail, String.class);
    }

    public String fallback(Throwable ex) {
        return "A: fallback → " + ex.getClass().getSimpleName();
    }
}

