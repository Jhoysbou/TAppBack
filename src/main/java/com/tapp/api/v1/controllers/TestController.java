package com.tapp.api.v1.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapp.api.v1.exceptions.InternalException;
import com.tapp.api.v1.exceptions.SignCheckException;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.TestService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.ParamsUtil;
import com.tapp.api.v1.utils.UserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    private final TestService testService = new TestService();
    private final UserService userService = new UserService();

    @GetMapping
    List<Test> getAllTests(@RequestHeader("params") String params) {
        log.info("getAllTests called");
        try {
            List<Test> tests = testService.getAllPublicTests().get();
            try {
                if (ParamsUtil.isValid(params)) {
                    log.debug("getAllTests sign check is done");
                    User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                    if (user.getRole().equals(UserRoles.admin.toString())) {
                        log.debug("admin verified id={}", user.getId());
                        return testService.getAllTests().get();
                    } else {
                        log.info("returning public tests");
                        return tests;
                    }
                }
            } catch (UserNotFoundException | SignCheckException | MalformedURLException e) {
                log.debug("getAllTests user not found or sign check failed. Returning public tests");
                return tests;
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("getAllTests error", e);
            throw new InternalException();
        }
        throw new InternalException();
    }

    @GetMapping("{id}")
    Test getTest(@RequestHeader("params") String params,
                 @PathVariable long id) {
        log.info("getTest called with id={}", id);
        try {
            if (ParamsUtil.isValid(params)) {
                log.debug("getTest sign check is done");
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                return testService.getTest(id).get();
            }
        } catch (InterruptedException | ExecutionException | SignCheckException | MalformedURLException e) {
            log.error("getTest error", e);
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
        log.info("saveTest called with title={}, date={}, description={}, timeToComplete={}",
                title, date, description, timeToComplete);
        try {
            if (ParamsUtil.isValid(params)) {
                log.debug("saveTest sign check is done");
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    log.debug("admin verified id={}", user.getId());
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
            log.warn("saveTest sign check failed with string={}", params);
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            log.error("saveTest error", e);
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
        log.info("updateTest called with title={}, date={}, description={}, timeToComplete={}",
                title, date, description, timeToComplete);
        try {
            if (ParamsUtil.isValid(params)) {
                log.debug("updateTest sign check is done");
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    log.debug("admin verified id={}", user.getId());
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
                }
            }
            throw new UnsupportedOperationException();

        } catch (SignCheckException e) {
            log.error("updateTest sign check failed");
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            log.error("updateTest error", e);
            throw new UnsupportedOperationException();
        }
    }

    @DeleteMapping("{id}")
    List<Test> deleteTest(@RequestHeader("params") String params,
                          @PathVariable long id) {
        log.info("deleteTest called with id={}", id);
        try {
            if (ParamsUtil.isValid(params)) {
                log.debug("deleteTest sign check is done");
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    log.debug("admin verified id={}", user.getId());
                    return testService.deleteTest(id).get();
                }
            }
            throw new UnsupportedOperationException();

        } catch (SignCheckException e) {
            log.error("deleteTest sign check failed");
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            log.error("deleteTest error", e);
            throw new InternalException();
        }
    }
}
