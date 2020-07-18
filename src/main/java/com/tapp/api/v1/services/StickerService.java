package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.StickerDao;
import com.tapp.api.v1.models.Sticker;

public class StickerService {
    private StickerDao stickerDao = new StickerDao();

    public void addSticker(final Sticker sticker) {
        stickerDao.save(sticker);
    }

    public void updateSticker(final Sticker sticker) {
        stickerDao.update(sticker);
    }

    public void deleteSticker(final long id) {
        stickerDao.deleteById(id);
    }
}
