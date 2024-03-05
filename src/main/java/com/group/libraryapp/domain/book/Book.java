package com.group.libraryapp.domain.book;

import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Book() {
    }

    public Book(String name) {
        if (isNotName(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    private boolean isNotName(String name) {
        return name == null || name.isBlank();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
