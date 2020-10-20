package com.tapp.api.v1.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("v1/time")
public class TimeController {

    @GetMapping
    long getTimer() {
        // Min latency is 25 millis
        return System.currentTimeMillis() - 25;
    }
}
