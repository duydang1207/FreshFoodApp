package com.example.freshfoodapp.Models;

import java.io.Serializable;

public class Category implements Serializable {
    Long id;
    String name;
    int parent;
    int children;

    public Category(Long id, String name, int parent, int children) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }
}
