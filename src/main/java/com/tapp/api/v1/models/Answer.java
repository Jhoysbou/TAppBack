package com.tapp.api.v1.models;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "answer_type")
    private String answerType;

    @Column(name = "answer")
    private String answer;

    @Column(name = "right_answer")
    private int rightAnswer;

    public Answer() {
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }


}
