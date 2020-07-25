package com.tapp.api.v1.controllers;


import com.tapp.api.v1.exceptions.UploadImageException;
import com.tapp.api.v1.services.MediaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("v1/media")
public class MediaController {
    private MediaService mediaService = new MediaService();

    @PostMapping()
    public String uploadTestImage(@RequestParam MultipartFile img) {
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
    }

}
