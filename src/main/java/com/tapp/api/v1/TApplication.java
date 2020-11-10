package com.tapp.api.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableAsync
public class TApplication {
    private static final Logger log = LoggerFactory.getLogger(TApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TApplication.class, args);
        log.info("Application started");
    }

    @Bean
    public Executor taskExecutor() {
        final int cores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(cores, 2 * cores, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(200));
        return executor;
    }
}
