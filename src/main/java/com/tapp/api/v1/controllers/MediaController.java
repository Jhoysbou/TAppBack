package com.tapp.api.v1.controllers;


import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("v1/media")
public class MediaController {

    @GetMapping("{name}")
    public @ResponseBody
    byte[] getImage(@PathVariable String name) throws java.io.IOException {
        InputStream resourceBuff = getClass().getClassLoader().getResourceAsStream("images/test/" + name);
        return IOUtils.toByteArray(resourceBuff);
    }

    @PutMapping("{name}")
    public void uploadImage(@PathVariable String name,
                          @RequestParam("file") MultipartFile file) {
//        Todo: implement saving
    }

}
