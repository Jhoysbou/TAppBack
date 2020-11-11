package com.tapp.api.v1.controllers;

import com.tapp.api.v1.TApplication;
import com.tapp.api.v1.exceptions.InternalException;
import com.tapp.api.v1.exceptions.NotFoundException;
import com.tapp.api.v1.exceptions.SignCheckException;
import com.tapp.api.v1.models.Sticker;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.StickerService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.ParamsUtil;
import com.tapp.api.v1.utils.UserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("v1/stickers")
public class StickerController {
    private static final Logger log = LoggerFactory.getLogger(StickerController.class);
    private StickerService stickerService = new StickerService();
    private UserService userService = new UserService();

    @GetMapping
    List<Sticker> getStickers() {
        log.info("getStickers called");
        try {
            return stickerService.getAll().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception in getStickers ", e);
            throw new InternalException();
        }
    }

    @GetMapping("/{id}")
    Sticker getSticker(@PathVariable long id) {
        log.info("getSticker with id={} called", id);
        try {
            return stickerService.get(id).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception in getSticker with id={}", id, e);
            throw new InternalException();
        }
    }

    @PostMapping
    List<Sticker> addSticker(@RequestHeader("params") String params,
                             @RequestParam long cost,
                             @RequestParam MultipartFile img,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String quote) {
        log.info("addSticker called with cost={}, img={}, name={}, description={}, quote={}",
                cost, img, name, description, quote);
        try {
            if (ParamsUtil.isValid(params)) {
                log.debug("addSticker sign check is done");
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    log.debug("admin verified id={}", user.getId());
                    Sticker sticker = new Sticker();
                    sticker.setCost(cost);
                    sticker.setDescription(description);
                    sticker.setName(name);
                    sticker.setQuote(quote);
                    return stickerService.addSticker(sticker, img).get();
                }
            }
            throw new NotFoundException();

        } catch (SignCheckException e) {
            log.warn("addSticker sign check failed with string={}", params);
            throw new NotFoundException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            log.error("addSticker error", e);
            throw new InternalException();
        }

    }

    @DeleteMapping("{id}")
    List<Sticker> deleteSticker(@RequestHeader("params") String params,
                                @PathVariable long id) {
        log.info("deleteSticker called with id={}", id);
        try {
            if (ParamsUtil.isValid(params)) {
                log.debug("deleteSticker sign check is done");
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    log.debug("admin verified id={}", user.getId());
                    return stickerService.deleteSticker(id).get();
                }
            }
            throw new NotFoundException();

        } catch (SignCheckException e) {
            log.error("addSticker sign check failed with string={}", params);
            throw new NotFoundException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            log.error("deleteSticker error", e);
            throw new InternalException();
        }
    }
}
