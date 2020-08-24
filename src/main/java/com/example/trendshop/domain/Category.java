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

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return title != null ? title.equals(category.title) : category.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", parent=" + parent +
                '}';
    }
}
