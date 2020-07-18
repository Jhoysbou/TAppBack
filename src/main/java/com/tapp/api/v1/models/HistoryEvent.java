package com.tapp.api.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class HistoryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "date")
    private String date;

    /*
     * 0 - question started
     * 1 - question failed
     * 2 - question passed
     * 3 - question skipped
     */
    @Column(name = "eventcode")
    private int eventCode;

    @Column(name = "score")
    private long score;

    public HistoryEvent() {
    }

    public HistoryEvent(User user, Test test, Question question) {
        this.user = user;
        this.test = test;
        this.question = question;
    }

    public HistoryEvent(User user, Test test, Question question, String date, int eventCode) {
        this.user = user;
        this.test = test;
        this.question = question;
        this.date = date;
        this.eventCode = eventCode;
    }

    public HistoryEvent(User user, Test test, Question question, String date, int eventCode, long score) {
        this.user = user;
        this.test = test;
        this.question = question;
        this.date = date;
        this.eventCode = eventCode;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
