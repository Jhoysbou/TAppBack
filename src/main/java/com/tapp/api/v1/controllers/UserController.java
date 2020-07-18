package com.tapp.api.v1.controllers;

import com.tapp.api.v1.exceptions.NotEnoughPointsException;
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

    @PostMapping("/start_question/")
    void startQuestion(@RequestBody HistoryEventHelper historyEventHelper) {
        historyService.startQuestion(historyEventHelper.getUserId(), historyEventHelper.getQuestionId());
    }

    @PostMapping("/pass_question/")
    void passQuestion(@RequestBody HistoryEventHelper historyEventHelper) {
        historyService.passQuestion(historyEventHelper.getUserId(), historyEventHelper.getQuestionId());
    }

    @PostMapping("/fail_question/")
    void failQuestion(@RequestBody HistoryEventHelper historyEventHelper) {
        historyService.passQuestion(historyEventHelper.getUserId(), historyEventHelper.getQuestionId());
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

//    just to get friendly mapping of json body
    public class HistoryEventHelper {
        private long questionId;
        private long userId;

        public HistoryEventHelper() {}

        public HistoryEventHelper(long questionId, long userId) {
            this.questionId = questionId;
            this.userId = userId;
        }

        public long getQuestionId() {
            return questionId;
        }

        public long getUserId() {
            return userId;
        }

        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }
    }

}
