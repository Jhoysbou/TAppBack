package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.HistoryEventDao;
import com.tapp.api.v1.dao.QuestionDao;
import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.dao.UserDao;
import com.tapp.api.v1.exceptions.QuestionNotFoundException;
import com.tapp.api.v1.exceptions.TestNotFoundException;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.HistoryEvent;
import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.utils.HistoryEventCode;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class HistoryService {
    private HistoryEventDao historyEventDao = new HistoryEventDao();
    private TestDao testDao = new TestDao();
    private UserDao userDao = new UserDao();
    private QuestionDao questionDao = new QuestionDao();

    public HistoryService() {
    }

    @Async
    public CompletableFuture<List<HistoryEvent>> getHistory(final long userId, final long testId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Test test = testDao.get(testId).orElseThrow(TestNotFoundException::new);
        return CompletableFuture.completedFuture((historyEventDao.getByUserTestHistory(user, test)));
    }

    @Async
    public void deleteHistory(final long userId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        historyEventDao.deleteByUser(user);
    }

    @Async
    public void startQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);

        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();

        List<HistoryEvent> history = historyEventDao.getByUserTestHistory(user, test);
        history.sort((o1, o2) -> (int) (o1.getDate() - o2.getDate()));

        final int size = history.size();
        HistoryEvent lastHistoryEvent = null;

        if (size > 0) {
            lastHistoryEvent = history.get(history.size() - 1);
        }
        if (lastHistoryEvent != null) {
            if (lastHistoryEvent.getQuestion().getId() != questionId
                    || (lastHistoryEvent.getEventCode() != HistoryEventCode.STARTED
                    && lastHistoryEvent.getEventCode() != HistoryEventCode.PASSED
                    && lastHistoryEvent.getEventCode() != HistoryEventCode.FAILED
                    && lastHistoryEvent.getEventCode() != HistoryEventCode.SKIPPED)) {
                final long score = lastHistoryEvent.getScore();

                HistoryEvent historyEvent = new HistoryEvent(user, test, question, System.currentTimeMillis(),
                        HistoryEventCode.STARTED, score);
                historyEventDao.save(historyEvent);
            }
        } else {
            HistoryEvent historyEvent = new HistoryEvent(user, test, question,
                    System.currentTimeMillis(), HistoryEventCode.STARTED, 0);
            historyEventDao.save(historyEvent);
        }

    }

    @Async
    public void passQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();

        List<HistoryEvent> history = historyEventDao.getByUserTestHistory(user, test);
        history.sort((o1, o2) -> (int) (o1.getDate() - o2.getDate()));

        final int size = history.size();
        HistoryEvent lastHistoryEvent = null;

        if (size > 0) {
            lastHistoryEvent = history.get(history.size() - 1);
        }

        if (lastHistoryEvent != null
                || lastHistoryEvent.getQuestion().getId() != questionId
                || (lastHistoryEvent.getEventCode() != HistoryEventCode.PASSED
                && lastHistoryEvent.getEventCode() != HistoryEventCode.FAILED
                && lastHistoryEvent.getEventCode() != HistoryEventCode.SKIPPED)) {
            final long score = lastHistoryEvent.getScore() + question.getReward();
            HistoryEvent historyEvent = new HistoryEvent(user, test, question, System.currentTimeMillis(),
                    HistoryEventCode.PASSED, score);
            historyEventDao.save(historyEvent);

            if (isFinished(question, test)) {
                user.setScore(user.getScore() + score);
                userDao.update(user);
            }
        }
    }

    @Async
    public void failQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();

        List<HistoryEvent> history = historyEventDao.getByUserTestHistory(user, test);
        history.sort((o1, o2) -> (int) (o1.getDate() - o2.getDate()));

        final int size = history.size();
        HistoryEvent lastHistoryEvent = null;

        if (size > 0) {
            lastHistoryEvent = history.get(history.size() - 1);
        }

        if (lastHistoryEvent != null
                || lastHistoryEvent.getQuestion().getId() != questionId
                || (lastHistoryEvent.getEventCode() != HistoryEventCode.PASSED
                && lastHistoryEvent.getEventCode() != HistoryEventCode.FAILED
                && lastHistoryEvent.getEventCode() != HistoryEventCode.SKIPPED)) {

            final long currentScore = lastHistoryEvent.getScore();
            final long reward = question.getReward();
            final long score = currentScore > reward ? currentScore - reward : 0;

            HistoryEvent historyEvent = new HistoryEvent(user, test, question, System.currentTimeMillis(),
                    HistoryEventCode.FAILED, score);
            historyEventDao.save(historyEvent);

            if (isFinished(question, test)) {
                user.setScore(user.getScore() + score);
                userDao.update(user);
            }
        }
    }

    @Async
    public void skipQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();

        List<HistoryEvent> history = historyEventDao.getByUserTestHistory(user, test);
        history.sort((o1, o2) -> (int) (o1.getDate() - o2.getDate()));

        final int size = history.size();
        HistoryEvent lastHistoryEvent = null;

        if (size > 0) {
            lastHistoryEvent = history.get(history.size() - 1);
        }

        if (lastHistoryEvent != null
                || lastHistoryEvent.getQuestion().getId() != questionId
                || (lastHistoryEvent.getEventCode() != HistoryEventCode.PASSED
                && lastHistoryEvent.getEventCode() != HistoryEventCode.FAILED
                && lastHistoryEvent.getEventCode() != HistoryEventCode.SKIPPED)) {

            final long score = lastHistoryEvent.getScore();

            HistoryEvent historyEvent = new HistoryEvent(user, test, question, System.currentTimeMillis(), HistoryEventCode.SKIPPED, score);
            historyEventDao.save(historyEvent);

            if (isFinished(question, test)) {
                user.setScore(user.getScore() + score);
                userDao.update(user);
            }
        }
    }


    private boolean isFinished(final Question question, final Test test) {
        final Set<Question> questionList = test.getQuestions();
        final long length = questionList.stream()
                .filter(q -> q.getSerialNumber() > question.getSerialNumber())
                .count();

        return length == 0;
    }
}
