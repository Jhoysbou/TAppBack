package com.tapp.api.v1.controllers;

import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.HistoryService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.controllers.support.HistoryEventSupport;
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

    @PostMapping("/start_question/")
    void startQuestion(@RequestBody HistoryEventSupport historyEventSupport) {
        historyService.startQuestion(historyEventSupport.getUserId(), historyEventSupport.getQuestionId());
    }

    @PostMapping("/pass_question/")
    void passQuestion(@RequestBody HistoryEventSupport historyEventSupport) {
        historyService.passQuestion(historyEventSupport.getUserId(), historyEventSupport.getQuestionId());
    }

    @PostMapping("/fail_question/")
    void failQuestion(@RequestBody HistoryEventSupport historyEventSupport) {
        historyService.passQuestion(historyEventSupport.getUserId(), historyEventSupport.getQuestionId());
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
