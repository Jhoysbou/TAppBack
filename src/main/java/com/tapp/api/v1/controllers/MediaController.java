package com.tapp.api.v1.controllers;


import com.tapp.api.v1.exceptions.SignCheckException;
import com.tapp.api.v1.exceptions.UploadImageException;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.services.MediaService;
import com.tapp.api.v1.services.UserService;
import com.tapp.api.v1.utils.ParamsUtil;
import com.tapp.api.v1.utils.UserRoles;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("v1/media")
public class MediaController {
    private MediaService mediaService = new MediaService();
    private UserService userService = new UserService();

    @PostMapping()
    public String uploadTestImage(@RequestHeader("params") String params, @RequestParam MultipartFile img) {
        try {
            if (ParamsUtil.isAuthentic(params)) {
                User user = userService.getUser(ParamsUtil.getUserId(params)).get();
                if (user.getRole().equals(UserRoles.admin.toString())) {
                    final String url;
                    try {
                        url = mediaService.uploadTestImage(img).get();
                        return "{\"url\": \""+ url +"\" }";
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    throw new UploadImageException();
                } else {
                    throw new UnsupportedOperationException();
                }

            } else {
                throw new UnsupportedOperationException();
            }
        } catch (SignCheckException e) {
            throw new UnsupportedOperationException();
        } catch (MalformedURLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
    }

}
