package com.example.trendshop.domain;


import java.util.Optional;

public class Category {
    private String title;
    private Optional<Category> parent;

    public Category(String title) {
        this.title = title;
    }

    public Category(String title, Optional<Category> parent) {
        this.title = title;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", parent=" + parent +
                '}';
    }
}
