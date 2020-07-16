package com.tapp.api.v1.services;

import com.tapp.api.v1.dao.HistoryEventDao;
import com.tapp.api.v1.dao.TestDao;
import com.tapp.api.v1.models.HistoryEvent;
import com.tapp.api.v1.utils.DateTimeFormat;
import com.tapp.api.v1.utils.HistoryEventCode;

import java.time.LocalDateTime;

public class HistoryService {
    private HistoryEventDao historyEventDao = new HistoryEventDao();
    private TestDao testDao = new TestDao();

    public HistoryService() {}

    public void startQuestion(final HistoryEvent historyEvent) {
        historyEvent.setDate(LocalDateTime.now().format(DateTimeFormat.getFormatter()));
        historyEvent.setEventCode(HistoryEventCode.STARTED);
        historyEventDao.save(historyEvent);
    }

//    public void failQuestion(final long userId, final long testId, final long questionId) {
//        HistoryEvent historyEvent = new HistoryEvent(userId, testId, questionId,
//                LocalDateTime.now().format(DateTimeFormat.getFormatter()), HistoryEventCode.FAILED);
//        historyEventDao.save(historyEvent);
//    }
//
//    public void passQuestion(final long userId, final long testId, final long questionId) {
//        HistoryEvent historyEvent = new HistoryEvent(userId, testId, questionId,
//                LocalDateTime.now().format(DateTimeFormat.getFormatter()), HistoryEventCode.PASSED);
//        historyEventDao.save(historyEvent);
//    }
}
