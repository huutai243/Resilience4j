package com.vn.rate_b.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/b")
public class BController {

    @GetMapping
    public String sayHi() {
        return " Hello from B";
    }
}

