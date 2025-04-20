package com.iuh.fit.downstream_service.downstream_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownstreamController {

    @GetMapping("/process")
    public String process(@RequestParam(defaultValue = "ok") String mode) {
        if ("fail".equalsIgnoreCase(mode)) {
            throw new RuntimeException("Simulated downstream failure");
        } else if ("slow".equalsIgnoreCase(mode)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "⏳ Response too slow";
        } else {
            return "✅ Success from downstream!";
        }
    }
}

