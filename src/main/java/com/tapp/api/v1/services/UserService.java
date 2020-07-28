package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.StickerDao;
import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.dao.UserDao;
import com.tapp.api.v1.exceptions.NotEnoughPointsException;
import com.tapp.api.v1.exceptions.StickerNotFoundException;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.Sticker;
import com.tapp.api.v1.models.User;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserService {
    private UserDao usersDao = new UserDao();
    private TestDao testDao = new TestDao();
    private StickerDao stickerDao = new StickerDao();

    public UserService() {
    }

    @Async
    public CompletableFuture<User> getUser(long id) {
        return CompletableFuture.completedFuture(usersDao.get(id).orElseThrow(UserNotFoundException::new));
    }

    @Async
    public void saveUser(User user) {
        usersDao.save(user);
    }

    @Async
    public void deleteUser(long id) {
        usersDao.deleteById(id);
    }

    @Async
    public void updateUser(User user) {
        usersDao.update(user);
    }

    @Async
    public CompletableFuture<List<User>> getAllUsers() {
        return CompletableFuture.completedFuture(usersDao.getAll());
    }

    @Async
    public CompletableFuture<User> buySticker(final long userId, final long stickerId) {
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
            return CompletableFuture.completedFuture(user);
        } else if (score >= cost) {
            user.addSticker(sticker);
            user.setScore(score - cost);
            usersDao.update(user);
            return CompletableFuture.completedFuture(user);
        } else {
            throw new NotEnoughPointsException();
        }
    }

}
