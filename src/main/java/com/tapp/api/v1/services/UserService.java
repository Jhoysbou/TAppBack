package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.StickerDao;
import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.dao.UserDao;
import com.tapp.api.v1.exceptions.NotEnoughPointsException;
import com.tapp.api.v1.exceptions.StickerNotFoundException;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.*;

import java.nio.channels.FileChannel;
import java.util.List;

public class UserService {
    private UserDao usersDao = new UserDao();
    private TestDao testDao = new TestDao();
    private StickerDao stickerDao = new StickerDao();

    public UserService() {
    }

    public User getUser(long id) {
        return usersDao.get(id).orElseThrow(UserNotFoundException::new);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(long id) {
        usersDao.deleteById(id);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> getAllUsers() {
        return usersDao.getAll();
    }

    public User buySticker(final long userId, final long stickerId) {
        final User user = usersDao.get(userId).orElseThrow(UserNotFoundException::new);
        final Sticker sticker = stickerDao.get(stickerId).orElseThrow(StickerNotFoundException::new);
        final long score = user.getScore();
        final long cost = sticker.getCost();

        boolean isContains = false;
        for (Sticker s : user.getStickers()) {
            if (s.getId() == stickerId) {
                isContains = true;
            }
        }

        if (isContains) {
            return user;
        } else if (score >= cost) {
            user.addSticker(sticker);
            user.setScore(score - cost);
            usersDao.update(user);
            return user;
        } else {
            throw new NotEnoughPointsException();
        }
    }

}
