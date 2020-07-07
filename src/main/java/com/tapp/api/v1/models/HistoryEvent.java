package com.tapp.api.v1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class HistoryEvent {
    @Id
    private long id;

    @Column(name = "userId")
    private long userId;

    @Column(name = "testId")
    private long testId;

    @Column(name = "questionNumber")
    private long questionNumber;

    @Column(name = "questionVariant")
    private long questionVariant;

    @Column(name = "date")
    private String date;

    /*
     * 0 - question started
     * 1 - question failed
     * 2 - question passed
     *
     */
    @Column(name = "eventCode")
    private int eventCode;

    public HistoryEvent(long id, long userId, long testId, long questionNumber, long questionVariant, String date, int eventCode) {
        this.id = id;
        this.userId = userId;
        this.testId = testId;
        this.questionNumber = questionNumber;
        this.questionVariant = questionVariant;
        this.date = date;
        this.eventCode = eventCode;
    }

    public HistoryEvent(long userId, long testId, long questionNumber, long questionVariant, String date, int eventCode) {
        this.userId = userId;
        this.testId = testId;
        this.questionNumber = questionNumber;
        this.questionVariant = questionVariant;
        this.date = date;
        this.eventCode = eventCode;
    }

    public HistoryEvent(long id, long userId, long testId, long questionNumber, long questionVariant, String date) {
        this.id = id;
        this.userId = userId;
        this.testId = testId;
        this.questionNumber = questionNumber;
        this.questionVariant = questionVariant;
        this.date = date;
    }

    public HistoryEvent(long userId, long testId, long questionNumber, long questionVariant, String startTime) {
        this.userId = userId;
        this.testId = testId;
        this.questionNumber = questionNumber;
        this.questionVariant = questionVariant;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getTestId() {
        return testId;
    }

    public long getQuestionNumber() {
        return questionNumber;
    }

    public long getQuestionVariant() {
        return questionVariant;
    }

    public String getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public void setQuestionNumber(long questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setQuestionVariant(long questionVariant) {
        this.questionVariant = questionVariant;
    }

    public void setDate(String startTime) {
        this.date = startTime;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }
}
