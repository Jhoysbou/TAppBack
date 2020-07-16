package com.tapp.api.v1.models;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class HistoryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

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

    public HistoryEvent(User user, Test test, Question question, String date, int eventCode) {
        this.user = user;
        this.test = test;
        this.question = question;
        this.date = date;
        this.eventCode = eventCode;
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
