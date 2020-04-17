package com.tapp.api.models;

import javax.persistence.*;

@Entity
@Table(name = "answers", schema = "tapp")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "answer_type")
    private AnswerType answer_type;

    @Column(name = "answer")
    private String answer;

    @Column(name = "right_answer")
    private int rightAnswer;

    public Answer() {
    }

    public long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public AnswerType getAnswer_type() {
        return answer_type;
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

    public void setAnswer_type(AnswerType answer_type) {
        this.answer_type = answer_type;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }


}
