package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.exceptions.TestNotFoundException;
import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.models.Test;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestService {
    private TestDao testDao = new TestDao();
    private MediaService mediaService = new MediaService();

    public TestService() {
    }

    @Async
    public CompletableFuture<Test> getTest(long id) {
        return CompletableFuture.completedFuture(testDao.get(id).orElseThrow(TestNotFoundException::new));
    }

    private CompletableFuture<Test> saveTest(Test test) {
        testDao.save(test);
        return CompletableFuture.completedFuture(test);
    }

    @Async
    public CompletableFuture<Test> saveTest(Test test, MultipartFile img) throws ExecutionException, InterruptedException {
        if (img != null) {
            final String url = mediaService.uploadTestImage(img).get();
            test.setImg(url);
        }


//        if (test.getQuestions() != null) {
//            test.getQuestions().forEach(question -> {
//                question.setTest(test);
//                if (question.getAnswers() != null) {
//                    question.getAnswers().forEach(answer -> answer.setQuestion(question));
//                }
//            });
//        }

        return saveTest(test);
    }

    @Async
    public CompletableFuture<List<Test>> deleteTest(long id) {
        testDao.deleteById(id);
        return getAllTests();
    }

    @Async
    public void updateTest(final Test test) throws ExecutionException, InterruptedException {
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
        oldTest.setQuestions(questions != null ? questions : oldTest.getQuestions());

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
}
