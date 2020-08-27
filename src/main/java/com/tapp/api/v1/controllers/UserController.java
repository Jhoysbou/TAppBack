package com.tapp.api.v1.controllers;

import com.tapp.api.v1.exceptions.SignCheckException;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.HistoryEvent;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.HistoryService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.HistoryEventCode;
import com.tapp.api.v1.utils.ParamsUtil;
import com.tapp.api.v1.utils.UserRoles;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("v1/users")
public class UserController {
    private UserService userService = new UserService();
    private HistoryService historyService = new HistoryService();

    @GetMapping
    CompletableFuture<List<User>> getAllUsers(@RequestHeader("params") String params) {
        try {
            if (ParamsUtil.isAuthentic(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    return userService.getAllUsers();
                }
            }

            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        }
    }

    @GetMapping("{id}")
    CompletableFuture<User> getUser(@PathVariable long id, @RequestHeader("params") String params) throws UserNotFoundException {
        try {
            if (ParamsUtil.isAuthentic(params) && id == ParamsUtil.getUserId(params)) {
                return userService.getUser(id);
            } else {
                throw new UserNotFoundException();
            }
        } catch (MalformedURLException | SignCheckException e) {
            throw new UserNotFoundException();
        }
    }

    @PutMapping("{id}")
    User saveUser(@PathVariable long id,
                  @RequestBody User user) {
        user.setId(id);
        return userService.saveUser(user);
    }

    @PostMapping("{userId}/send_event/{questionId}")
    void startQuestion(@RequestHeader("params") String params,
                       @PathVariable long userId,
                       @PathVariable long questionId,
                       @RequestBody int eventCode) throws UnsupportedOperationException {
        try {
            if (ParamsUtil.isAuthentic(params) && userId == ParamsUtil.getUserId(params)) {
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
        } catch (SignCheckException | MalformedURLException e) {
            throw new UnsupportedOperationException();
        }
    }


    @GetMapping("{userId}/get_history/{testId}")
    CompletableFuture<List<HistoryEvent>> getHistory(@RequestHeader("params") String params,
                                                     @PathVariable long userId,
                                                     @PathVariable long testId) {
        try {
            if (ParamsUtil.isAuthentic(params) && userId == ParamsUtil.getUserId(params)) {
                return historyService.getHistory(userId, testId);
            } else {
                throw new UnsupportedOperationException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            throw new UnsupportedOperationException();
        }
    }

    @PatchMapping("{userId}/buy_sticker/{stickerId}")
    CompletableFuture<User> buySticker(@RequestHeader("params") String params,
                                       @PathVariable long userId,
                                       @PathVariable long stickerId) {
        try {
            if (ParamsUtil.isAuthentic(params) && userId == ParamsUtil.getUserId(params)) {
                return userService.buySticker(userId, stickerId);
            } else {
                throw new UnsupportedOperationException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            throw new UnsupportedOperationException();
        }
    }

    @PatchMapping("{userId}/set_active_sticker/{stickerId}")
    CompletableFuture<User> setActiveSticker(@RequestHeader("params") String params,
                                             @PathVariable long userId,
                                             @PathVariable long stickerId) {
        try {
            if (ParamsUtil.isAuthentic(params) && userId == ParamsUtil.getUserId(params)) {
                return userService.setActiveSticker(stickerId, userId);
            } else {
                throw new UnsupportedOperationException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            throw new UnsupportedOperationException();
        }
    }


}
