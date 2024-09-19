package com.jromeo.webservice.book.controller;

import com.jromeo.webservice.book.entity.Author;
import com.jromeo.webservice.book.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Jonas Torjman
 */
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/authors")
@RestController
public class AuthorController {

    private final AuthorService authorService;

    // Get all Authors (no auth)
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    // Find author by ID (no auth)
//    @GetMapping("/{id}")
//    public Author findAuthorByID(@PathVariable Integer id) {
//        return authorService.getAuthorById(id);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> findAuthorByID(@PathVariable Integer id) {
        try {
            Author author = authorService.getAuthorById(id);
            return ResponseEntity.ok(author);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // Add an Author to an existing Book.
    @PostMapping("/{authorId}/book/{bookId}")
    public Author addBookToAuthor(@PathVariable Integer authorId, @PathVariable Integer bookId) {
        return authorService.addBookToAuthor(authorId, bookId);
    }

    // Create new Author (admin_role)
    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    // Update existing Author by ID (admin_role)
    @PatchMapping("/{id}")
    public Author updateAuthor(@PathVariable Integer id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    // Delete one Author by ID (admin_role)
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
    }
}
