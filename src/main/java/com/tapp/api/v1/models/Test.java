package com.tapp.api.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "img")
    private String pathToImage;

    @Column(name = "title")
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
    private List<UsersTests> usersTests;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Question> questions;

    @Column(name = "date")
    private String creationDate;

    public Test() {
    }

    public Test(String img, String title, String description, String creationDate) {
        this.pathToImage = img;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
    }

    public Test(String img, String title, String description, List<Question> questions, String creationDate) {
        this.pathToImage = img;
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.creationDate = creationDate;
    }

    public List<UsersTests> getUsersTests() {
        return usersTests;
    }

    public void setUsersTests(List<UsersTests> usersTests) {
        this.usersTests = usersTests;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
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

    public void setCreationDate(String creationDate) {
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

    public List<Question> getQuestions() {
        return questions;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
