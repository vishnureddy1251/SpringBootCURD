package com.example.SpringBootCURDApp.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="Books")
@ToString
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;

    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Book() {
    }

}
