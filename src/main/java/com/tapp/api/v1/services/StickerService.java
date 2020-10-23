package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.StickerDao;
import com.tapp.api.v1.exceptions.StickerNotFoundException;
import com.tapp.api.v1.models.Sticker;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class StickerService {
    private StickerDao stickerDao = new StickerDao();
    private MediaService mediaService = new MediaService();

    @Async
    public CompletableFuture<List<Sticker>> getAll() {
        return CompletableFuture.completedFuture(stickerDao.getAll());
    }

    @Async
    public CompletableFuture<Sticker> get(long id) {
        return CompletableFuture.completedFuture(stickerDao.get(id).orElseThrow(StickerNotFoundException::new));
    }

    @Async
    public CompletableFuture<List<Sticker>> addSticker(final Sticker sticker, final MultipartFile img) throws ExecutionException, InterruptedException {
        if (img != null) {
            final String url = mediaService.uploadStickerImage(img).get();
            sticker.setImg(url);
        }

        stickerDao.save(sticker);
        return getAll();
    }

    @Async
    public void updateSticker(final Sticker sticker) {
        stickerDao.update(sticker);
    }

    @Async
    public CompletableFuture<List<Sticker>> deleteSticker(final long id) {
        stickerDao.deleteById(id);
        return getAll();
    }
}
