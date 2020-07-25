package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.StickerDao;
import com.tapp.api.v1.models.Sticker;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class StickerService {
    private StickerDao stickerDao = new StickerDao();

    @Async
    public CompletableFuture<Long> addSticker(final Sticker sticker) {
        stickerDao.save(sticker);
        return CompletableFuture.completedFuture(sticker.getId());
    }

    @Async
    public void updateSticker(final Sticker sticker) {
        stickerDao.update(sticker);
    }

    @Async
    public void deleteSticker(final long id) {
        stickerDao.deleteById(id);
    }
}
