package com.tapp.api.models;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "users")
public class User {
    @Id
    private long id;

    @Column(name = "level")
    private int level;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_tests",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "test_id")}
    )
    private HashSet<Test> unfinishedTests;

    public User() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLevel(int progress) {
        this.level = progress;
    }

    public void setUnfinishedTests(HashSet<Test> unfinishedTests) {
        this.unfinishedTests = unfinishedTests;
    }

    public long getId() {
        return id;
    }

    public int level() {
        return level;
    }

    public HashSet<Test> getUnfinishedTests() {
        return unfinishedTests;

    }
}
