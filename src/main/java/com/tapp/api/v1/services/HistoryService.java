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
import com.tapp.api.v1.utils.DateTimeFormat;
import com.tapp.api.v1.utils.HistoryEventCode;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class HistoryService {
    private HistoryEventDao historyEventDao = new HistoryEventDao();
    private TestDao testDao = new TestDao();
    private UserDao userDao = new UserDao();
    private QuestionDao questionDao = new QuestionDao();

    public HistoryService() {
    }

    public List<HistoryEvent> getHistory(final long userId, final long testId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Test test = testDao.get(testId).orElseThrow(TestNotFoundException::new);
        return historyEventDao.getByUserTestHistory(user, test);
    }

    public void startQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();

        List<HistoryEvent> history = historyEventDao.getByUserTestHistory(user, test);
        history.sort(new TimeOrderComparator());

        final int size = history.size();
        HistoryEvent lastHistoryEvent = null;

        if (size > 0) {
            lastHistoryEvent = history.get(history.size() - 1);
        }

        if (lastHistoryEvent == null
                || lastHistoryEvent.getQuestion().getId() != questionId
                || (lastHistoryEvent.getEventCode() != HistoryEventCode.STARTED
                && lastHistoryEvent.getEventCode() != HistoryEventCode.PASSED
                && lastHistoryEvent.getEventCode() != HistoryEventCode.FAILED)) {
            HistoryEvent historyEvent = new HistoryEvent(user, test, question,
                    LocalDateTime.now().format(DateTimeFormat.getFormatter()), HistoryEventCode.STARTED, 0);
            historyEventDao.save(historyEvent);
        }
    }

    public void passQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();

        List<HistoryEvent> history = historyEventDao.getByUserTestHistory(user, test);
        history.sort(new TimeOrderComparator());
        HistoryEvent lastHistoryEvent = history.get(history.size() - 1);
        System.out.println(lastHistoryEvent.getEventCode());
        if (lastHistoryEvent.getQuestion().getId() != questionId
                || (lastHistoryEvent.getEventCode() != HistoryEventCode.PASSED
                && lastHistoryEvent.getEventCode() != HistoryEventCode.FAILED)) {
            final long score = lastHistoryEvent.getScore() + question.getReward();
            HistoryEvent historyEvent = new HistoryEvent(user, test, question,
                    LocalDateTime.now().format(DateTimeFormat.getFormatter()), HistoryEventCode.PASSED, score);
            historyEventDao.save(historyEvent);
        }
    }

    public void failQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();

        List<HistoryEvent> history = historyEventDao.getByUserTestHistory(user, test);
        history.sort(new TimeOrderComparator());
        HistoryEvent lastHistoryEvent = history.get(history.size() - 1);

        if (lastHistoryEvent.getQuestion().getId() != questionId
                || lastHistoryEvent.getEventCode() != HistoryEventCode.PASSED
                || lastHistoryEvent.getEventCode() != HistoryEventCode.FAILED) {

            final long currentScore = lastHistoryEvent.getScore();
            final long reward = question.getReward();
            final long score = currentScore > reward ? currentScore - reward : 0;

            HistoryEvent historyEvent = new HistoryEvent(user, test, question,
                    LocalDateTime.now().format(DateTimeFormat.getFormatter()), HistoryEventCode.PASSED, score);
            historyEventDao.save(historyEvent);
        }
    }

    class TimeOrderComparator implements Comparator<HistoryEvent> {
        @Override
        public int compare(HistoryEvent o1, HistoryEvent o2) {
            LocalDateTime date1 = LocalDateTime.parse(o1.getDate(), DateTimeFormat.getFormatter());
            LocalDateTime date2 = LocalDateTime.parse(o2.getDate(), DateTimeFormat.getFormatter());
            return date1.compareTo(date2);
        }
    }


}
