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
//
//    public void addTest(long id, long testId) {
//        User user = usersDao.get(id).orElseThrow(UserNotFoundException::new);
//        List<UsersTests> tests = user.getUsersTests();
//        Test newTest = testDao.get(testId).orElseThrow(TestNotFoundException::new);
//        Question question = newTest.getQuestions().get(0);
//
//        UsersTests usersTests = new UsersTests();
//        usersTests.setTest(newTest);
//        usersTests.setQuestion(question);
//        usersTests.setUser(user);
//        usersTests.setStart_time(LocalDateTime.now().format(DateTimeFormat.getFormatter()));
//        usersTestsDao.save(usersTests);
//    }
}
