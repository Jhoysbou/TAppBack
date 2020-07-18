package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.StickerDao;
import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.dao.UserDao;
import com.tapp.api.v1.exceptions.StickerNotFoundException;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.*;

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

    public void addSticker(final long userId, final long stickerId) {
        User user = usersDao.get(userId).orElseThrow(UserNotFoundException::new);
        user.addSticker(stickerDao.get(stickerId).orElseThrow(StickerNotFoundException::new));
        usersDao.update(user);
    }

}
