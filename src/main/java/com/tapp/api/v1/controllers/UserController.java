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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("v1/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService = new UserService();
    private final HistoryService historyService = new HistoryService();

    @GetMapping
    List<User> getAllUsers(@RequestHeader("params") String params) {
        log.info("getAllUsers called");
        try {
            if (ParamsUtil.isValid(params)) {
                log.debug("getAllUsers sign check is done");
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    log.debug("getAllUser admin verified id={}", user.getId());
                    return userService.getAllUsers().get();
                }
            }
            throw new UserNotFoundException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            log.error("getAllUsers error", e);
            throw new UserNotFoundException();
        } catch (SignCheckException e) {
            log.error("getAllUsers sign check failed with string={}", params, e);
            throw new UserNotFoundException();
        }
    }

    @GetMapping("{id}")
    User getUser(@PathVariable long id, @RequestHeader("params") String params) throws UserNotFoundException {
        log.info("getUser called with id={}", id);
        try {
            if (ParamsUtil.isValid(params) && id == ParamsUtil.getUserId(params)) {
                log.debug("getUser sign check is done");
                return userService.getUser(id).get();
            } else {
                log.error("getUser param's id does not match request id");
                throw new UserNotFoundException();
            }
        } catch (MalformedURLException | SignCheckException e) {
            log.error("getUser sign check failed with string={}", params);
            throw new UserNotFoundException();
        } catch (InterruptedException | ExecutionException e) {
            log.error("getUser error", e);
        }

        throw new InternalException();
    }

    @PutMapping("{id}")
    User saveUser(@RequestHeader("params") String params,
                  @PathVariable long id,
                  @RequestBody User user) {
        log.info("saveUser called with id={}, user={}", id, user);
        try {
            if (ParamsUtil.isValid(params) && id == ParamsUtil.getUserId(params)) {
                log.debug("saveUser sign check is done");
                user.setId(id);
                return userService.saveUser(user);
            } else {
                log.error("saveUser param's id does not match request id");
                throw new NotFoundException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            log.error("sign check failed", e);
            throw new NotFoundException();
        }
    }

    @PostMapping("{userId}/send_event/{questionId}")
    void startQuestion(@RequestHeader("params") String params,
                       @PathVariable long userId,
                       @PathVariable long questionId,
                       @RequestBody int eventCode) throws NotFoundException {
        log.info("startQuestion called with userId={}, questionId={}, eventCode={}", userId, questionId, eventCode);
        try {
            if (ParamsUtil.isValid(params) && userId == ParamsUtil.getUserId(params)) {
                log.debug("sign check is done");
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
            log.error("sign check failed", e);
            throw new NotFoundException();
        }
    }

    @GetMapping("{userId}/get_history/{testId}")
    List<HistoryEvent> getHistory(@RequestHeader("params") String params,
                                  @PathVariable long userId,
                                  @PathVariable long testId) {
        log.info("getHistory called with userId={}, testId={}", userId, testId);
        try {
            if (ParamsUtil.isValid(params) && userId == ParamsUtil.getUserId(params)) {
                log.debug("sign check is done");
                return historyService.getHistory(userId, testId).get();
            } else {
                log.error("saveUser param's userId does not match request id");
                throw new NotFoundException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            log.error("sign check failed", e);
            throw new NotFoundException();
        } catch (InterruptedException | ExecutionException e) {
            log.error("error", e);
        }
        throw new InternalException();
    }

    @PatchMapping("{userId}/buy_sticker/{stickerId}")
    User buySticker(@RequestHeader("params") String params,
                    @PathVariable long userId,
                    @PathVariable long stickerId) {
        log.info("buySticker called with userId={}, stickerId={}", userId, stickerId);
        try {
            if (ParamsUtil.isValid(params) && userId == ParamsUtil.getUserId(params)) {
                log.debug("sign check is done");
                return userService.buySticker(userId, stickerId).get();
            } else {
                log.error("buySticker param's userId does not match request id");
                throw new NotFoundException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            log.error("sign check failed", e);
            throw new NotFoundException();
        } catch (InterruptedException | ExecutionException e) {
            log.error("error", e);
        }
        throw new InternalException();
    }

    @PatchMapping("{userId}/set_active_sticker/{stickerId}")
    User setActiveSticker(@RequestHeader("params") String params,
                          @PathVariable long userId,
                          @PathVariable long stickerId) {
        log.info("setActiveSticker called with userId={}, stickerId={}", userId, stickerId);
        try {
            if (ParamsUtil.isValid(params) && userId == ParamsUtil.getUserId(params)) {
                log.debug("sign check is done");
                return userService.setActiveSticker(stickerId, userId).get();
            } else {
                log.error("setActiveSticker param's userId does not match request id");
                throw new NotFoundException();
            }
        } catch (SignCheckException | MalformedURLException e) {
            log.error("sign check failed", e);
            throw new NotFoundException();
        } catch (InterruptedException | ExecutionException e) {
            log.error("error", e);
        }
        throw new InternalException();
    }
}
