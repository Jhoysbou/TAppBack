package com.tapp.api.v1.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("v1/time")
public class TimeController {
    private static final Logger log = LoggerFactory.getLogger(TimeController.class);

    @GetMapping
    long getTimer() {
        log.info("getTime called");
        // Min latency is 25 millis
        return System.currentTimeMillis() - 25;
    }
}
