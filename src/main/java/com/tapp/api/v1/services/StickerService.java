package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.StickerDao;
import com.tapp.api.v1.exceptions.StickerNotFoundException;
import com.tapp.api.v1.models.Sticker;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.security.Signature;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class StickerService {
    private StickerDao stickerDao = new StickerDao();
    private MediaService mediaService = new MediaService();

    @Async
    public List<Sticker> getAll() {
        return stickerDao.getAll();
    }

    @Async
    public Sticker get(long id) {
        return stickerDao.get(id).orElseThrow(StickerNotFoundException::new);
    }

    @Async
    public CompletableFuture<List<Sticker>> addSticker(final Sticker sticker, final MultipartFile img) throws ExecutionException, InterruptedException {
        if (img != null) {
            final String url = mediaService.uploadTestImage(img).get();
            sticker.setImg(url);
        }

        stickerDao.save(sticker);
        return CompletableFuture.completedFuture(getAll());
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
