package com.tapp.api.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "reward")
    private int reward;

    @Column(name = "img")
    private String img;

    @JsonIgnore
    @OneToMany(mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<HistoryEvent> history;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @OneToMany(mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Answer> answers;

    @Column(name = "serial_number")
    private int serialNumber;

    public Question() {
    }

    public Question(String questionText, String img, int serialNumber) {
        this.questionText = questionText;
        this.img = img;
        this.serialNumber = serialNumber;
    }


    public void setImg(String pathToImage) {
        this.img = pathToImage;
    }

    public String getImg() {
        return img;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
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
