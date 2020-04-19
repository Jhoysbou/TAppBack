package com.tapp.api.v1.controllers;

import com.google.gson.Gson;
import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.services.TestService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("v1/tests")
public class TestController {
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private TestService testService = new TestService();
    private Gson gson = new Gson();


    @GetMapping
    String getAllTests() {
        return gson.toJson(testService.getAllTests());
    }

    @GetMapping("{id}")
    String getTest(@PathVariable long id) {
        return gson.toJson(testService.getTest(id));
    }

    @PostMapping
    void saveTest(@RequestBody Test test) {
        testService.saveTest(test);
    }

    @DeleteMapping("{id}")
    void deleteTest(@PathVariable long id) {
        testService.deleteTest(id);
    }
}
