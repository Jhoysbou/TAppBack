package com.tapp.api.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "img")
    private String img;

    @Column(name = "title")
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
    private Set<HistoryEvent> history;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Question> questions;

    @Column(name = "date")
    private String date;

    @Column(name = "max_score")
    private long maxScore;

    @Column(name = "complete_time")
    private String timeToComplete;

    public Test() {}


    public Test(String img, String title, String description, String date) {
        this.img = img;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Test(String img, String title, String description, Set<Question> questions, String date) {
        this.img = img;
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.date = date;
    }

    public long getMaxScore() {
        return maxScore;
    }

    public String getTimeToComplete() {
        return timeToComplete;
    }

    public void setMaxScore(long maxScore) {
        this.maxScore = maxScore;
    }

    public void setTimeToComplete(String timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public Set<HistoryEvent> getHistory() {
        return history;
    }

    public void setHistory(Set<HistoryEvent> history) {
        this.history = history;
    }

    public void setImg(String pathToImage) {
        this.img = pathToImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void setDate(String creationDate) {
        this.date = creationDate;
    }

    public long getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public String getDate() {
        return date;
    }
}
