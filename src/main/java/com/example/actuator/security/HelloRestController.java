package com.example.actuator.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello")
public class HelloRestController {

    @GetMapping
    public String sayHello() {
        return "It works";
    }
}
