package com.vn.service_b.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/b")
public class BController {
    // GET /b?fail=true → lỗi 500
    @GetMapping
    public ResponseEntity<String> endpointB(
            @RequestParam(defaultValue = "false") boolean fail) {
        if (fail) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("B: simulated failure");
        }
        return ResponseEntity.ok("B: OK");
    }
}

