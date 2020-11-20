package com.tapp.api.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<HistoryEvent> history;

    @Column(name = "description")
    private String description;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "test",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Question> questions = new HashSet<>();

    @Column(name = "date")
    private long date;

    @Column(name = "max_score")
    private long maxScore;

    @Column(name = "complete_time")
    private String timeToComplete;

    public Test() {}

    public void setId(long id) {
        this.id = id;
    }

    public long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(long maxScore) {
        this.maxScore = maxScore;
    }

    public String getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(String timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public Set<HistoryEvent> getHistory() {
        return history;
    }

    public void setHistory(Set<HistoryEvent> history) {
        this.history = history;
    }

    public long getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String pathToImage) {
        this.img = pathToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long creationDate) {
        this.date = creationDate;
    }
}
