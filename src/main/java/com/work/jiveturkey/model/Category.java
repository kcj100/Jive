package com.work.jiveturkey.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Book> books;

    public Category() {
    }

    public Category(Long category_id, String categoryName) {
        this.category_id = category_id;
        this.categoryName = categoryName;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long id) {
        this.category_id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
