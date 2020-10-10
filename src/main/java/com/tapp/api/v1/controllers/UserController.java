package com.tapp.api.v1.controllers;

import com.tapp.api.v1.exceptions.InternalException;
import com.tapp.api.v1.exceptions.NotFoundException;
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
    private final UserService userService = new UserService();
    private final HistoryService historyService = new HistoryService();

    @GetMapping
    List<User> getAllUsers(@RequestHeader("params") String params) {
        try {
            if (ParamsUtil.isValid(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    return userService.getAllUsers().get();
                }
            }

            throw new UserNotFoundException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new UserNotFoundException();
        } catch (SignCheckException e) {
            throw new UserNotFoundException();
        }
    }

    @GetMapping("{id}")
    User getUser(@PathVariable long id, @RequestHeader("params") String params) throws UserNotFoundException {
        try {
            if (ParamsUtil.isValid(params) && id == ParamsUtil.getUserId(params)) {
                return userService.getUser(id).get();
            } else {
                throw new UserNotFoundException();
            }
        } catch (MalformedURLException | SignCheckException e) {
            throw new UserNotFoundException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        throw new InternalException();
    }

    @PutMapping("{id}")
    User saveUser(@RequestHeader("params") String params,
                  @PathVariable long id,
                  @RequestBody User user) {
        try {
            if (ParamsUtil.isValid(params) && id == ParamsUtil.getUserId(params)) {
                user.setId(id);
                return userService.saveUser(user);
            } else {
                throw new NotFoundException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            throw new NotFoundException();
        }

    }

    @PostMapping("{userId}/send_event/{questionId}")
    void startQuestion(@RequestHeader("params") String params,
                       @PathVariable long userId,
                       @PathVariable long questionId,
                       @RequestBody int eventCode) throws NotFoundException {
        try {
            if (ParamsUtil.isValid(params) && userId == ParamsUtil.getUserId(params)) {
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
            throw new NotFoundException();
        }
    }

    @GetMapping("{userId}/get_history/{testId}")
    List<HistoryEvent> getHistory(@RequestHeader("params") String params,
                                                     @PathVariable long userId,
                                                     @PathVariable long testId) {
        try {
            if (ParamsUtil.isValid(params) && userId == ParamsUtil.getUserId(params)) {
                return historyService.getHistory(userId, testId).get();
            } else {
                throw new NotFoundException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            throw new NotFoundException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        throw new InternalException();
    }

    @PatchMapping("{userId}/buy_sticker/{stickerId}")
    User buySticker(@RequestHeader("params") String params,
                                       @PathVariable long userId,
                                       @PathVariable long stickerId) {
        try {
            if (ParamsUtil.isValid(params) && userId == ParamsUtil.getUserId(params)) {
                return userService.buySticker(userId, stickerId).get();
            } else {
                throw new NotFoundException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            throw new NotFoundException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        throw new InternalException();
    }

    @PatchMapping("{userId}/set_active_sticker/{stickerId}")
    User setActiveSticker(@RequestHeader("params") String params,
                                             @PathVariable long userId,
                                             @PathVariable long stickerId) {
        try {
            if (ParamsUtil.isValid(params) && userId == ParamsUtil.getUserId(params)) {
                return userService.setActiveSticker(stickerId, userId).get();
            } else {
                throw new NotFoundException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            throw new NotFoundException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        throw new InternalException();
    }


}
