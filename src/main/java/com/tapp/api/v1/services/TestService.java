package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.models.Test;

import java.util.List;

public class TestService {
    private TestDao testDao = new TestDao();

    public TestService() {
    }

    public Test getTest(long id) {
        return testDao.get(id).orElseThrow(UserNotFoundException::new);
    }

    public void saveTest(Test test) {
        test.getQuestions().stream().forEach(question -> question.setTest(test));

        testDao.save(test);
    }

    public void deleteTest(long id) {
        testDao.deleteById(id);
    }

    public void updateTest(Test test) {
        testDao.update(test);
    }

    public List<Test> getAllTests() {
        return testDao.getAll();
    }
}
