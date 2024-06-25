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

//        // 20 MB = 20 * 1024 * 1024 bytes
//        int size = 50 * 1024 * 1024;
//
//        // Creating a byte array of 20 MB
//        byte[] largeArray = new byte[size];
//
//        // Optionally fill the array with some data
//        for (int i = 0; i < largeArray.length; i++) {
//            largeArray[i] = (byte) (i % 256);
//        }

        return "Hello World from pod: " + hostname;
    }
}