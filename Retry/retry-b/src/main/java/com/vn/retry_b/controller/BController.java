package com.vn.retry_b.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/b")
public class BController {

    @GetMapping
    public String endpointB() {
        return " B is alive";
    }
}

