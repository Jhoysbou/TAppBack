package com.tapp.api.v1.controllers;

import com.google.gson.Gson;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.services.TestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/tests")
public class TestController {
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

    @PutMapping("{id}")
    void saveTest() {
        Test Test = new Test();

        testService.saveTest(Test);
    }

    @DeleteMapping("{id}")
    void deleteTest(@PathVariable long id) {
        testService.deleteTest(id);
    }
}
