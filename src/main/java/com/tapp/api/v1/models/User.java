package com.tapp.api.v1.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private long id;

    @Column(name = "score")
    private int score;

    @Column(name = "school")
    private String school;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UsersTests> usersTests;

    public User() {
    }

    public User(long id, int score, int age, String school) {
        this.id = id;
        this.score = score;
        this.school = school;
        this.age = age;
    }

    public User(int level, int age, String school) {
        this.score = level;
        this.school = school;
        this.age = age;
    }

    public User(int score, int age) {
        this.score = score;
        this.age = age;
    }

    public User(int level, String school) {
        this.score = level;
        this.school = school;
    }

    public User(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int progress) {
        this.score = progress;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int level() {
        return score;
    }

    public List<UsersTests> getUsersTests() {
        return usersTests;

    }

    public void setUsersTests(List<UsersTests> usersTests) {
        this.usersTests = usersTests;
    }
}
