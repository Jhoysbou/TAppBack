package com.tapp.api.v1.controllers;

import com.tapp.api.v1.exceptions.SignCheckException;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.TestService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.ParamsUtil;
import com.tapp.api.v1.utils.UserRoles;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("v1/tests")
public class TestController {
    private TestService testService = new TestService();
    private UserService userService = new UserService();

    @GetMapping
    CompletableFuture<List<Test>> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("{id}")
    CompletableFuture<Test> getTest(@PathVariable long id) {
        return testService.getTest(id);
    }

    @PostMapping
    CompletableFuture<Test> saveTest(@RequestHeader("params") String params,
                                     @RequestParam String title,
                                     @RequestParam String date,
                                     @RequestParam(required = false) MultipartFile img,
                                     @RequestParam(required = false) String description,
                                     @RequestParam(required = false) String timeToComplete) {
        try {
            if (ParamsUtil.isValid(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    System.out.println(title);
                    final Test test = new Test();
                    test.setDescription(description);
                    test.setTitle(title);
                    test.setTimeToComplete(timeToComplete);
                    test.setDate(date);

                    return testService.saveTest(test, img);
                }
            }
            throw new UnsupportedOperationException();

        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }

    }

    @PatchMapping
    void updateTest(@RequestHeader("params") String params,
                    @RequestBody Test test) {
        try {
            if (ParamsUtil.isValid(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    testService.updateTest(test);
                } else {
                    throw new UnsupportedOperationException();
                }
            } else {
                throw new UnsupportedOperationException();
            }


        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
    }

    @DeleteMapping("{id}")
    CompletableFuture<List<Test>> deleteTest(@RequestHeader("params") String params,
                                             @PathVariable long id) {

        try {
            if (ParamsUtil.isValid(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    return testService.deleteTest(id);
                }
            }
            throw new UnsupportedOperationException();

        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
    }
}
