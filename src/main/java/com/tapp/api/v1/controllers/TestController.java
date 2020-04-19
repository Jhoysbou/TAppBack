package com.tapp.api.v1.controllers;

import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.services.TestService;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("v1/tests")
public class TestController {
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private TestService testService = new TestService();


    @GetMapping
    List<Test> getAllTests() {
        List<Test> tests = testService.getAllTests();
        return tests;
    }

    @GetMapping("{id}")
    Test getTest(@PathVariable long id) {
        return testService.getTest(id);
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
