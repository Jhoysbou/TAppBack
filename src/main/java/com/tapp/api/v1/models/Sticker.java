package com.tapp.api.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stickers")
public class Sticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cost")
    private long cost;

    @Column(name = "img")
    private String img;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "quote")
    private String quote;

    @JsonIgnore
    @ManyToMany(mappedBy = "stickers")
    private List<User> holders;

    public Sticker() {}

    public Sticker(long cost, String img, String description, String quote) {
        this.cost = cost;
        this.img = img;
        this.description = description;
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getCost() {
        return cost;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public String getQuote() {
        return quote;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
