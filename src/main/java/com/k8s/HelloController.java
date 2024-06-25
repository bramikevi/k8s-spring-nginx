package com.k8s;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${HOSTNAME}")
    private String hostname;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World from pod: " + hostname;
    }
}