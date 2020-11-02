package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.StickerDao;
import com.tapp.api.v1.exceptions.StickerNotFoundException;
import com.tapp.api.v1.models.Sticker;
import com.tapp.api.v1.models.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
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
    public CompletableFuture<List<Sticker>> deleteSticker(final long id) {
        Sticker sticker;
        try {
            sticker = get(id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new StickerNotFoundException();
        }
        List<User> holders = sticker.getHolders();
        holders.stream().map(user -> {
            if (user.getActiveSticker() == sticker) {
                Set<Sticker> stickerSet = user.getStickers();
                if (stickerSet.size() > 1) {
//                    Cannot throw exception because of the line above
                    user.setActiveSticker(stickerSet.stream().reduce((acc, s) -> s == sticker ? acc : s).get());
                } else {
                    throw new StickerNotFoundException();
                }
            }

            return user;
        });
        stickerDao.deleteById(id);
        return getAll();
    }
}
