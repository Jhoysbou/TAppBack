package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.dao.UserDao;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.*;

import java.util.List;

public class UserService {
    private UserDao usersDao = new UserDao();
    private TestDao testDao = new TestDao();

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

}
