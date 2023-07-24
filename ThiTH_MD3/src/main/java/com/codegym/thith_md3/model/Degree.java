package com.codegym.thith_md3.model;

public class Degree {
    private long id;
    private String name;

    public Degree(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Degree() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
