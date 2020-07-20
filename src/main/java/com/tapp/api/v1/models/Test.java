package com.tapp.api.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


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
    private List<HistoryEvent> history;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Question> questions;

    @Column(name = "date")
    private String date;

    public Test() {}



    public Test(String img, String title, String description, String date) {
        this.img = img;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Test(String img, String title, String description, List<Question> questions, String date) {
        this.img = img;
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.date = date;
    }

    public List<HistoryEvent> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryEvent> history) {
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

    public void setQuestions(List<Question> questions) {
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

    public List<Question> getQuestions() {
        return questions;
    }

    public String getDate() {
        return date;
    }
}
