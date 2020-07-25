package com.tapp.api.v1.controllers;

import com.tapp.api.v1.models.Sticker;
import com.tapp.api.v1.services.StickerService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("v1/stickers")
public class StickerController {
    private StickerService stickerService = new StickerService();

    @PostMapping
    String addSticker(@RequestBody Sticker sticker) throws ExecutionException, InterruptedException {
        long id = stickerService.addSticker(sticker).get();
        return "{'id': "+ id +" }";
    }

    @PatchMapping
    void updateSticker(@RequestBody Sticker sticker) {
        stickerService.updateSticker(sticker);
    }

    @DeleteMapping("{id}")
    void deleteSticker(@PathVariable long id) {
        stickerService.deleteSticker(id);
    }
}
