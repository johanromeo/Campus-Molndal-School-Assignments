package com.jromeo.webservice.book.service;


import com.jromeo.webservice.book.entity.Book;
import com.jromeo.webservice.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Sandra Jeppson Kristiansson
 */
@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Non existing Id"));
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    public Book updateBook(Integer id, Book book) {
        Book bookToUpdate = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Non existing Id"));
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setGenre(book.getGenre());
        bookRepository.save(bookToUpdate);
        return bookToUpdate;
    }

    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Non existing Id"));
        bookRepository.delete(book);
    }
}