package com.tapp.api.v1.models;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class HistoryEvent {
    @Id
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "testid")
    private Test test;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionid")
    private Question question;

    @Column(name = "date")
    private String date;

    /*
     * 0 - question started
     * 1 - question failed
     * 2 - question passed
     *
     */
    @Column(name = "eventcode")
    private int eventCode;

    public HistoryEvent() {}

    public HistoryEvent(User user, Test test, Question question) {
        this.user = user;
        this.test = test;
        this.question = question;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Test getTest() {
        return test;
    }

    public Question getQuestion() {
        return question;
    }

    public String getDate() {
        return date;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }
}
