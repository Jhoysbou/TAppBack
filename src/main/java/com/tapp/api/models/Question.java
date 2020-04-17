package com.tapp.api.models;

import javax.persistence.*;
import java.util.LinkedList;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "question_text")
    private String questionText;

    @ManyToOne(fetch = FetchType.LAZY)
    private Test test;

    @OneToMany(mappedBy = "answers", cascade = CascadeType.ALL, orphanRemoval = true)
    private LinkedList<Answer> answers;

    public Question() {
    }

    public long getId() {
        return id;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswers(LinkedList<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public LinkedList<Answer> getAnswers() {
        return answers;
    }



}
