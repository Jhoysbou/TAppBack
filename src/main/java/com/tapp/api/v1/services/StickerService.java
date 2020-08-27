package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.StickerDao;
import com.tapp.api.v1.exceptions.StickerNotFoundException;
import com.tapp.api.v1.models.Sticker;
import org.springframework.scheduling.annotation.Async;

import java.security.Signature;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class StickerService {
    private StickerDao stickerDao = new StickerDao();

    @Async
    public List<Sticker> getAll() {
        return stickerDao.getAll();
    }

    @Async
    public Sticker get(long id) {
        return stickerDao.get(id).orElseThrow(StickerNotFoundException::new);
    }

    @Async
    public CompletableFuture<Sticker> addSticker(final Sticker sticker) {
        stickerDao.save(sticker);
        return CompletableFuture.completedFuture(sticker);
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
