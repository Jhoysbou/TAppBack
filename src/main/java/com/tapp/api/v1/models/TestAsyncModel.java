package com.tapp.api.v1.models;

public class TestAsyncModel {
    private long id;
    private String name;

    public TestAsyncModel() {}

    public TestAsyncModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
