package com.tapp.api.v1.controllers.support;

public class HistoryEventSupport {
    private long questionId;
    private long userId;

    public HistoryEventSupport() {}

    public HistoryEventSupport(long questionId, long userId) {
        this.questionId = questionId;
        this.userId = userId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
