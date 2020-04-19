package com.tapp.api.v1.models;

import javax.persistence.*;

@Entity
@Table(name = "users_tests")
public class UsersTests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "question_id")
    private Question question;

    @Column(name = "time")
    private String time;

    public UsersTests() {
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

    public void setTime(String time) {
        this.time = time;
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

    public String getTime() {
        return time;
    }
}
