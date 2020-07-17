package com.tapp.api.v1.models;

import javax.persistence.*;

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

    @Column(name = "description")
    private String description;

    @Column(name = "quote")
    private String quote;

    public Sticker(long cost) {
        this.cost = cost;
    }

    public Sticker(long cost, String img, String description, String quote) {
        this.cost = cost;
        this.img = img;
        this.description = description;
        this.quote = quote;
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
