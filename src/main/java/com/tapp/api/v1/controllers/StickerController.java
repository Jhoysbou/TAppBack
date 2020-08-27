package com.tapp.api.v1.controllers;

import com.tapp.api.v1.exceptions.SignCheckException;
import com.tapp.api.v1.models.Sticker;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.StickerService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.ParamsUtil;
import com.tapp.api.v1.utils.UserRoles;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("v1/stickers")
public class StickerController {
    private StickerService stickerService = new StickerService();
    private UserService userService = new UserService();

    @GetMapping
    List<Sticker> getSticker() {
        return stickerService.getAll();
    }

    @GetMapping("/{id}")
    Sticker getSticker(@PathVariable long id) {
        return stickerService.get(id);
    }

    @PostMapping
    CompletableFuture<Sticker> addSticker(@RequestHeader("params") String params,
                                          @RequestBody Sticker sticker) {
        try {
            if (ParamsUtil.isAuthentic(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    return stickerService.addSticker(sticker);
                }
            }
            throw new UnsupportedOperationException();

        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }

    }

    @PatchMapping
    void updateSticker(@RequestHeader("params") String params,
                       @RequestBody Sticker sticker) {
        try {
            if (ParamsUtil.isAuthentic(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    stickerService.updateSticker(sticker);
                }
            }
            throw new UnsupportedOperationException();

        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
    }

    @DeleteMapping("{id}")
    void deleteSticker(@RequestHeader("params") String params,
                       @PathVariable long id) {
        try {
            if (ParamsUtil.isAuthentic(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    stickerService.deleteSticker(id);
                }
            }
            throw new UnsupportedOperationException();

        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
    }
}
