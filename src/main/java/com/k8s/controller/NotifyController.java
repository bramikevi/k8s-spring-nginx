package com.k8s.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notify")
public class NotifyController {
    @GetMapping
    public String notification() {
        return "notify";
    }
}
