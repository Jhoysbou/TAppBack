package com.tapp.api.v1.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private long id;

    @Column(name = "level")
    private int level;

    @Column(name = "school")
    private String school;

    @Column(name = "age")
    private int age;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_tests",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "test_id")}
    )
    private List<Test> tests;

    public User() {
    }

    public User(long id, int level, int age, String school) {
        this.id = id;
        this.level = level;
        this.school = school;
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public String getSchool() {
        return school;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLevel(int progress) {
        this.level = progress;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public long getId() {
        return id;
    }

    public int level() {
        return level;
    }

    public List<Test> getTests() {
        return tests;

    }
}
