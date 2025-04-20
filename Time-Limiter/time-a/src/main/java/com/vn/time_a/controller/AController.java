package com.vn.time_a.controller;

import com.vn.time_a.service.AService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/a")
public class AController {

    private final AService aService;

    public AController(AService aService) {
        this.aService = aService;
    }

    @GetMapping
    public CompletableFuture<String> testTimeLimiter() {
        return aService.callB();
    }
}
