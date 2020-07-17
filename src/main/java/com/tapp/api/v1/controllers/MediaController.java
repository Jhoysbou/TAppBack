package com.tapp.api.v1.controllers;


import com.tapp.api.v1.services.MediaService;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("v1/media")
public class MediaController {
    private MediaService mediaService = new MediaService();

    @PostMapping()
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        mediaService.uploadImage();
    }

}
