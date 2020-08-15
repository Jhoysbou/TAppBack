package com.tapp.api.v1.controllers;

import com.tapp.api.v1.exceptions.SaveTestException;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.services.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("v1/tests")
public class TestController {
    private TestService testService = new TestService();

    @GetMapping
    CompletableFuture<List<Test>> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("{id}")
    CompletableFuture<Test> getTest(@PathVariable long id) {
        return testService.getTest(id);
    }

    @PostMapping
    String saveTest(@RequestBody Test test) {
        try {
            long id = testService.saveTest(test).get();
            return "{'id': "+ id +" }";
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        throw new SaveTestException();
    }

    @DeleteMapping("{id}")
    void deleteTest(@PathVariable long id) {
        testService.deleteTest(id);
    }
}
