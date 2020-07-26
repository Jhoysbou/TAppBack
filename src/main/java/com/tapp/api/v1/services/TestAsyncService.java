package com.tapp.api.v1.services;


import com.tapp.api.v1.models.TestAsyncModel;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TestAsyncService {
    public TestAsyncService() {}

    @Async
    public CompletableFuture<TestAsyncModel> longProcess() throws InterruptedException {
        TestAsyncModel testAsyncModel = new TestAsyncModel(1, "1");
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(testAsyncModel);
    }

}
