package com.tapp.api.v1.controllers;

import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.HistoryEvent;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.HistoryService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.HistoryEventHelper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("v1/users")
public class UserController {
    private UserService userService = new UserService();
    private HistoryService historyService = new HistoryService();

    @GetMapping
    CompletableFuture<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    CompletableFuture<User> getUser(@PathVariable long id) {
        try {
            return userService.getUser(id);
        } catch (NullPointerException e) {
            throw new UserNotFoundException();
        }
    }

    @PutMapping("{id}")
    void saveUser(@PathVariable long id,
                  @RequestBody User user) {
        user.setId(id);
        userService.saveUser(user);
    }

    @PostMapping("{userId}/start_question/{questionId}")
    void startQuestion(@PathVariable long userId, @PathVariable long questionId) {
        historyService.startQuestion(userId, questionId);
    }

    @PostMapping("{userId}/pass_question/{questionId}")
    void passQuestion(@PathVariable long userId, @PathVariable long questionId) {
        historyService.passQuestion(userId, questionId);
    }

    @PostMapping("{userId}/fail_question/{questionId}")
    void failQuestion(@PathVariable long userId, @PathVariable long questionId) {
        historyService.passQuestion(userId, questionId);
    }

    @PostMapping("{userId}/skip_history/{questionId}")
    void skipQuestion(@PathVariable long userId, @PathVariable long questionId) {
        historyService.skipQuestion(userId, questionId);
    }

    @GetMapping("{userId}/get_history/{testId}")
    CompletableFuture<List<HistoryEvent>> getHistory(@PathVariable long userId, @PathVariable long testId) {
        return historyService.getHistory(userId, testId);
    }



    @PostMapping("{userId}/buy_sticker/{stickerId}")
    CompletableFuture<User> buySticker(@PathVariable long userId, @PathVariable long stickerId) {
        return userService.buySticker(userId, stickerId);
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
