package com.pfe.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Auth service is running 🔐";
    }

    @GetMapping("/secure")
    public String secure() {
        return "You are authenticated 🎉";
    }
}
