package com.vn.time_b.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b")
public class BController {

    @GetMapping
    public String simulateSlowCall() throws InterruptedException {
        Thread.sleep(3000); // 3 giây
        return " B completed after 3 seconds";
    }
}
