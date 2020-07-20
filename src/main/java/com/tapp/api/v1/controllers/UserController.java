package com.tapp.api.v1.controllers;

import com.tapp.api.v1.models.HistoryEvent;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.HistoryService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.HistoryEventHelper;
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

    @PostMapping("/start_question")
    void startQuestion(@RequestBody HistoryEventHelper historyEventHelper) {
        historyService.startQuestion(historyEventHelper.getUserId(), historyEventHelper.getQuestionId());
    }

    @PostMapping("/pass_question")
    void passQuestion(@RequestBody HistoryEventHelper historyEventHelper) {
        historyService.passQuestion(historyEventHelper.getUserId(), historyEventHelper.getQuestionId());
    }

    @PostMapping("/fail_question")
    void failQuestion(@RequestBody HistoryEventHelper historyEventHelper) {
        historyService.passQuestion(historyEventHelper.getUserId(), historyEventHelper.getQuestionId());
    }

    @GetMapping("{userId}/get_history/{testId}")
    List<HistoryEvent> getHistory(@PathVariable long userId, @PathVariable long testId) {
        return historyService.getHistory(userId, testId);
    }

    @PostMapping("{userId}/buy_sticker/{stickerId}")
    User buySticker(@PathVariable long userId, @PathVariable long stickerId) {
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
