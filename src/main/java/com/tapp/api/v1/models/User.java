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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UsersTests> usersTests;

    public User() {
    }

    public User(long id, int level, int age, String school) {
        this.id = id;
        this.level = level;
        this.school = school;
        this.age = age;
    }

    public User(int level, int age, String school) {
        this.level = level;
        this.school = school;
        this.age = age;
    }

    public User(int level, int age) {
        this.level = level;
        this.age = age;
    }

    public User(int level, String school) {
        this.level = level;
        this.school = school;
    }

    public User(int level) {
        this.level = level;
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

    public void setUsersTests(List<UsersTests> usersTests) {
        this.usersTests = usersTests;
    }

    public long getId() {
        return id;
    }

    public int level() {
        return level;
    }

    public List<UsersTests> getUsersTests() {
        return usersTests;

    }
}
