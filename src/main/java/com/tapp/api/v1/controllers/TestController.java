package com.tapp.api.v1.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapp.api.v1.exceptions.InternalException;
import com.tapp.api.v1.exceptions.SignCheckException;
import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.TestService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.ParamsUtil;
import com.tapp.api.v1.utils.UserRoles;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("v1/tests")
public class TestController {
    private final TestService testService = new TestService();
    private final UserService userService = new UserService();

    @GetMapping
    List<Test> getAllTests(@RequestHeader("params") String params) {
        try {
            if (ParamsUtil.isValid(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    return testService.getAllTests().get();
                } else {
                    return testService.getAllPublicTests().get();
                }
            }
        } catch (InterruptedException | ExecutionException | SignCheckException | MalformedURLException e) {
            e.printStackTrace();
        }
        throw new InternalException();
    }

    @GetMapping("{id}")
    Test getTest(@RequestHeader("params") String params,
                 @PathVariable long id) {
        try {
            if (ParamsUtil.isValid(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                return testService.getTest(id).get();
            }
        } catch (InterruptedException | ExecutionException | SignCheckException | MalformedURLException e) {
            e.printStackTrace();
        }
        throw new InternalException();
    }

    @PostMapping
    Test saveTest(@RequestHeader("params") String params,
                  @RequestParam String title,
                  @RequestParam String date,
                  @RequestParam(required = false) MultipartFile img,
                  @RequestParam(required = false) String description,
                  @RequestParam(required = false) String timeToComplete) {
        try {
            if (ParamsUtil.isValid(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    final Test test = new Test();
                    test.setDescription(description);
                    test.setTitle(title);
                    test.setTimeToComplete(timeToComplete);
                    test.setDate(date);

                    return testService.saveTest(test, img).get();
                }
            }
            throw new UnsupportedOperationException();

        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new InternalException();
        }

    }

    @PatchMapping
    void updateTest(@RequestHeader("params") String params,
                    @RequestParam long id,
                    @RequestParam String title,
                    @RequestParam String date,
                    @RequestParam(required = false) String questions,
                    @RequestParam(required = false) int maxScore,
                    @RequestParam(required = false) MultipartFile img,
                    @RequestParam(required = false) String description,
                    @RequestParam(required = false) String timeToComplete) {
        try {
            if (ParamsUtil.isValid(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    final Test test = new Test();
                    test.setId(id);
                    test.setMaxScore(maxScore);

                    ObjectMapper mapper = new ObjectMapper();
                    Set<Question> questionSet;
                    try {
                        questionSet = mapper.readValue(questions, new TypeReference<Set<Question>>() {
                        });
                    } catch (IOException e) {
                        throw new InternalException();
                    }

                    Set<Question> qSet = test.getQuestions();
                    qSet.addAll(questionSet);

                    test.setDescription(description);
                    test.setTitle(title);
                    if (timeToComplete != "null" && timeToComplete != null) {
                        test.setTimeToComplete(timeToComplete);
                    }
                    test.setDate(date);

                    testService.updateTest(test, img);
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
    List<Test> deleteTest(@RequestHeader("params") String params,
                          @PathVariable long id) {

        try {
            if (ParamsUtil.isValid(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    return testService.deleteTest(id).get();
                }
            }
            throw new UnsupportedOperationException();

        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }
}
