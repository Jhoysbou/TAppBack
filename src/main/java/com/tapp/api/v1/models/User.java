package com.tapp.api.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    private long id;

    @Column(name = "score")
    private long score;

    @Column(name = "school")
    private String school;

    @Column(name = "bdate")
    private String bdate;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<HistoryEvent> history;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_stickers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "sticker_id")}
    )
    private Set<Sticker> stickers = new HashSet<>();

    public User() {
    }

    public User(long id, long score, String school, String bdate, String role) {
        this.id = id;
        this.score = score;
        this.school = school;
        this.bdate = bdate;
        this.role = role;
    }

    public User(long id, int score, String bdate, String school) {
        this.id = id;
        this.score = score;
        this.school = school;
        this.bdate = bdate;
    }

    public User(int level, String bdate, String school) {
        this.score = level;
        this.school = school;
        this.bdate = bdate;
    }

    public User(int score, String bdate) {
        this.score = score;
        this.bdate = bdate;
    }

    public User(int score) {
        this.score = score;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<HistoryEvent> getHistory() {
        return history;
    }


    public void setHistory(List<HistoryEvent> history) {
        this.history = history;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Sticker> getStickers() {
        return stickers;
    }

    public void setStickers(Set<Sticker> stickers) {
        this.stickers = stickers;
    }

    public void addSticker(Sticker sticker) {
        this.stickers.add(sticker);
    }
}
