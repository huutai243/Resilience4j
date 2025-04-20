package com.vn.retry_a.controller;

import com.vn.retry_a.service.AService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/a")
public class AController {
    private final AService aService;

    public AController(AService aService) {
        this.aService = aService;
    }

    @GetMapping
    public String callB() {
        return aService.callB();
    }
}

