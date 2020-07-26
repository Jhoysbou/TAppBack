package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.Test;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TestService {
    private TestDao testDao = new TestDao();

    public TestService() {
    }

    @Async
    public CompletableFuture<Test> getTest(long id) {
        return CompletableFuture.completedFuture(testDao.get(id).orElseThrow(UserNotFoundException::new));
    }

    @Async
    public CompletableFuture<Long> saveTest(Test test) {
        test.getQuestions().stream().forEach(question -> question.setTest(test));
        testDao.save(test);

        return CompletableFuture.completedFuture(test.getId());
    }

    @Async
    public void deleteTest(long id) {
        testDao.deleteById(id);
    }

    @Async
    public void updateTest(Test test) {
        testDao.update(test);
    }

    @Async
    public CompletableFuture<List<Test>> getAllTests() {
        return CompletableFuture.completedFuture(testDao.getAll());
    }
}
