package com.tapp.api.v1.controllers;

import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.HistoryService;
import com.tapp.api.v1.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {
    private UserService userService = new UserService();
    private HistoryService historyService = new HistoryService();

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PutMapping("{id}")
    void saveUser(@PathVariable long id,
                  @RequestBody User user) {
        user.setId(id);
        userService.saveUser(user);
    }

    @PostMapping("{userId}/startQuestion/")
    void startQuestion(@PathVariable long userId,
                       @RequestParam long testId,
                       @RequestParam int questionNumber,
                       @RequestParam int questionVariant) {

        historyService.startQuestion(userId,
                testId,
                questionNumber,
                questionVariant);
    }

    @PostMapping("{userId}/passQuestion/")
    void passQuestion(@PathVariable long userId,
                       @RequestParam long testId,
                       @RequestParam int questionNumber,
                       @RequestParam int questionVariant) {

        historyService.passQuestion(userId,
                testId,
                questionNumber,
                questionVariant);
    }

    @PostMapping("{userId}/failQuestion/")
    void failQuestion(@PathVariable long userId,
                       @RequestParam long testId,
                       @RequestParam int questionNumber,
                       @RequestParam int questionVariant) {

        historyService.failQuestion(userId,
                testId,
                questionNumber,
                questionVariant);
    }

    @PatchMapping("{id}")
    void updateUser(@PathVariable long id,
                    @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
