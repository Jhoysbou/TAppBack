package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.exceptions.TestNotFoundException;
import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.utils.DateTimeFormat;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class TestService {
    private final TestDao testDao = new TestDao();
    private final MediaService mediaService = new MediaService();

    public TestService() {
    }

    @Async
    public CompletableFuture<Test> getTest(long id) {
        return CompletableFuture.completedFuture(testDao.get(id).orElseThrow(TestNotFoundException::new));
    }

    private CompletableFuture<Test> saveTest(final Test test) {
        testDao.save(test);
        return CompletableFuture.completedFuture(test);
    }

    private Test uploadAndSetImg(final Test test, final MultipartFile img) throws ExecutionException, InterruptedException {
        if (img != null) {
            final String url = mediaService.uploadTestImage(img).get();
            test.setImg(url);
        }

        return test;
    }

    @Async
    public CompletableFuture<Test> saveTest(Test test, MultipartFile img) throws ExecutionException, InterruptedException {
        test = uploadAndSetImg(test, img);

        return saveTest(test);
    }

    @Async
    public CompletableFuture<List<Test>> deleteTest(long id) {
        testDao.deleteById(id);
        return getAllTests();
    }

    @Async
    public void updateTest(Test test, final MultipartFile img) throws ExecutionException, InterruptedException {
        test = uploadAndSetImg(test, img);

        Test savedTest = null;

        try {
            final long id = test.getId();
            savedTest = getTest(id).get();
        } catch (TestNotFoundException e) {
            saveTest(test);
        }

        if (savedTest != null) {
            final Test mergedTest = mergeTests(savedTest, test);
            testDao.update(mergedTest);
        }

    }

    private Test mergeTests(Test oldTest, final Test newTest) {
        String img = newTest.getImg();
        oldTest.setImg(img != null ? img : oldTest.getImg());

        String title = newTest.getTitle();
        oldTest.setTitle(title != null ? title : oldTest.getTitle());

        String description = newTest.getDescription();
        oldTest.setDescription(description != null ? description : oldTest.getDescription());

        Set<Question> questions = newTest.getQuestions();
        if (questions != null) {
            Set<Question> oldQuestions = oldTest.getQuestions();
            oldQuestions.clear();
            oldQuestions.addAll(questions);

            oldTest.getQuestions().forEach(question -> {
                question.setTest(oldTest);
                if (question.getAnswers() != null) {
                    question.getAnswers().forEach(answer -> answer.setQuestion(question));
                }
            });
        }

        String date = newTest.getDate();
        oldTest.setDate(date != null ? date : oldTest.getDate());

        long maxScore = newTest.getMaxScore();
        oldTest.setMaxScore(maxScore > 0 ? maxScore : oldTest.getMaxScore());

        String timeToComplete = newTest.getTimeToComplete();
        oldTest.setTimeToComplete(timeToComplete != null ? timeToComplete : oldTest.getTimeToComplete());

        return oldTest;
    }


    @Async
    public CompletableFuture<List<Test>> getAllTests() {
        return CompletableFuture.completedFuture(testDao.getAll());
    }

    @Async
    public CompletableFuture<List<Test>> getAllPublicTests() {
        List<Test> tests = testDao.getAll();
        tests = tests.stream().filter(test -> {
            LocalDateTime date = LocalDateTime.parse(test.getDate(), DateTimeFormat.getFormatter());
            return date.isBefore(LocalDateTime.now());
        }).collect(Collectors.toList());

        return CompletableFuture.completedFuture(tests);
    }
}
