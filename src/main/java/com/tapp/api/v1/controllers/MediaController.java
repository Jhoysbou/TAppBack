package com.tapp.api.v1.controllers;


import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
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
}
