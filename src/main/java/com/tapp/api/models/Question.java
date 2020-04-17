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
    @OneToMany(mappedBy = "answers")
    private LinkedList<Answer> answers;

}
