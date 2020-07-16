package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.HistoryEventDao;
import com.tapp.api.v1.dao.QuestionDao;
import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.dao.UserDao;
import com.tapp.api.v1.exceptions.NotFoundException;
import com.tapp.api.v1.exceptions.QuestionNotFoundException;
import com.tapp.api.v1.exceptions.UserNotFoundException;
import com.tapp.api.v1.models.HistoryEvent;
import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.utils.DateTimeFormat;
import com.tapp.api.v1.utils.HistoryEventCode;

import java.time.LocalDateTime;

public class HistoryService {
    private HistoryEventDao historyEventDao = new HistoryEventDao();
    private TestDao testDao = new TestDao();
    private UserDao userDao = new UserDao();
    private QuestionDao questionDao = new QuestionDao();

    public HistoryService() {
    }

    public void startQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();
        HistoryEvent historyEvent = new HistoryEvent(user, test, question,
                LocalDateTime.now().format(DateTimeFormat.getFormatter()), HistoryEventCode.STARTED);
        historyEventDao.save(historyEvent);
    }

    public void passQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();
        HistoryEvent historyEvent = new HistoryEvent(user, test, question,
                LocalDateTime.now().format(DateTimeFormat.getFormatter()), HistoryEventCode.PASSED);
        historyEventDao.save(historyEvent);
    }

    public void failQuestion(final long userId, final long questionId) {
        User user = userDao.get(userId).orElseThrow(UserNotFoundException::new);
        Question question = questionDao.get(questionId).orElseThrow(QuestionNotFoundException::new);
        Test test = question.getTest();
        HistoryEvent historyEvent = new HistoryEvent(user, test, question,
                LocalDateTime.now().format(DateTimeFormat.getFormatter()), HistoryEventCode.FAILED);
        historyEventDao.save(historyEvent);
    }
}
