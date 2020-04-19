package com.tapp.api.v1.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "img")
    private String pathToImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    @Column(name = "number")
    private int SerialNumber;

    public Question() {
    }


    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setSerialNumber(int serialNumber) {
        SerialNumber = serialNumber;
    }

    public int getSerialNumber() {
        return SerialNumber;
    }


    public void setTest(Test test) {
        this.test = test;
    }

    public Test getTest() {
        return test;
    }


    public long getId() {
        return id;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }


}
