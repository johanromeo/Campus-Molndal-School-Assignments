package com.jromeo.webservice.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: Johan Romeo
 */

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "book_title", unique = true, nullable = false)
    private String title;

    @Column(name = "book_genre", nullable = false)
    private String genre;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
