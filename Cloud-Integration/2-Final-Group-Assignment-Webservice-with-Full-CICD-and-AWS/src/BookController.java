package com.jromeo.webservice.book.controller;

import com.jromeo.webservice.book.entity.Book;
import com.jromeo.webservice.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Jonas Torjman
 */
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;

    // Get all Books (no auth)
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Find book by ID (no auth)
//    @GetMapping("/{id}")
//    public Book findBookByID(@PathVariable Integer id) {
//        return bookService.getBookById(id);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookByID(@PathVariable Integer id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new Book (admin_role)
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // Update existing Book by ID (admin_role)
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    // Delete one Book by ID (admin_role)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}
