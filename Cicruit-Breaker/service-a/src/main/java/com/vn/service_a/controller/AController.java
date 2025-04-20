package com.vn.service_a.controller;


import com.vn.service_a.service.AService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoke")
public class AController {
    private final AService svc;

    public AController(AService svc) {
        this.svc = svc;
    }

    // GET /invoke?fail=true
    @GetMapping
    public String invoke(@RequestParam(defaultValue = "false") boolean fail) {
        return svc.callB(fail);
    }
}

