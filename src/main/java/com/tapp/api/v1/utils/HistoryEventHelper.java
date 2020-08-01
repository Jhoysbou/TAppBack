package com.tapp.api.v1.utils;

// just to get friendly mapping of json body
public class HistoryEventHelper {
    private long questionId;
    private long userId;

    public HistoryEventHelper() {}

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