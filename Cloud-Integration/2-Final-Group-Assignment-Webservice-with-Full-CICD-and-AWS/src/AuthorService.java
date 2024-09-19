package com.jromeo.webservice.book.service;

import com.jromeo.webservice.book.entity.Author;
import com.jromeo.webservice.book.entity.Book;
import com.jromeo.webservice.book.repository.AuthorRepository;
import com.jromeo.webservice.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Sandra Jeppson Kristiansson
 */

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;


    public Author createAuthor(Author author) {

        return authorRepository.save(author);
    }

    public Author addBookToAuthor(Integer authorId, Integer bookId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Non existing Id"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Non existing Id"));
        author.getBooks().add(book);
        book.setAuthor(author);
        bookRepository.save(book);
        authorRepository.save(author);
        return author;
    }

    public Author getAuthorById(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Non existing Id"));
        return author;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }

    public Author updateAuthor(int id, Author author) {
        Author updatedAuthor = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Non existing Id"));
        updatedAuthor.setName(author.getName());
        authorRepository.save(updatedAuthor);
        return updatedAuthor;
    }

    public void deleteAuthor(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Non existing Id"));
        authorRepository.delete(author);
    }
}
