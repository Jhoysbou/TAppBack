package com.tapp.api.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "question",
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY)
    private List<HistoryEvent> history;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Answer> answers;

    @Column(name = "explain")
    private String explain;

    @Column(name = "serial_number")
    private int serialNumber;

    public Question() {
    }

    public Question(String questionText, String img, int serialNumber) {
        this.questionText = questionText;
        this.img = img;
        this.serialNumber = serialNumber;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String pathToImage) {
        this.img = pathToImage;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public List<HistoryEvent> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryEvent> history) {
        this.history = history;
    }

    public long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

}
