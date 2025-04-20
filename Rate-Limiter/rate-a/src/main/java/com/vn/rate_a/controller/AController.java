package com.vn.rate_a.controller;

import com.vn.rate_a.service.AService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/a")
public class AController {

    private final AService aService;

    public AController(AService aService) {
        this.aService = aService;
    }

    @GetMapping
    public String testRate() {
        return aService.callB();
    }
}
