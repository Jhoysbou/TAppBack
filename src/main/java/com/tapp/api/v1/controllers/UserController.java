package com.tapp.api.v1.controllers;

import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.HistoryEvent;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.HistoryService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.HistoryEventCode;
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

    @PostMapping("{userId}/send_event/{questionId}")
    void startQuestion(@PathVariable long userId, @PathVariable long questionId, @RequestBody int eventCode) {
        switch (eventCode) {
            case HistoryEventCode.FAILED:
                historyService.failQuestion(userId, questionId);
                break;
            case HistoryEventCode.PASSED:
                historyService.passQuestion(userId, questionId);
                break;
            case HistoryEventCode.SKIPPED:
                historyService.skipQuestion(userId, questionId);
                break;
            case HistoryEventCode.STARTED:
                historyService.startQuestion(userId, questionId);
                break;
        }
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
