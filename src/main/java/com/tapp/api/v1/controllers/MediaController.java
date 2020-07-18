package com.tapp.api.v1.controllers;


import com.tapp.api.v1.services.MediaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("v1/media")
public class MediaController {
    private MediaService mediaService = new MediaService();

    @PostMapping()
    public String uploadImage(@RequestParam MultipartFile file) {
        return mediaService.uploadTestImage(file);
    }


}
