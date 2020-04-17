package com.tapp.api.models;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;

@Entity
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "image")
    private String pathToImage;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "tests", cascade = CascadeType.ALL, orphanRemoval = true)
    private LinkedList<Question> questions;

    public Test() {
    }

    @Column(name = "date")
    private Date creationDate;

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
