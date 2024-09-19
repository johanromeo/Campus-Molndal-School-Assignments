package com.jromeo.webservice;

import com.jromeo.webservice.book.entity.Author;
import com.jromeo.webservice.book.entity.Book;
import com.jromeo.webservice.book.repository.AuthorRepository;
import com.jromeo.webservice.book.repository.BookRepository;
import com.jromeo.webservice.book.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Author: Sandra Jeppson Kristiansson
 */

@SpringBootTest
public class AuthorServiceTests {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        author.setName("Test Author");

        when(authorRepository.save(author)).thenReturn(author);

        Author savedAuthor = authorService.createAuthor(author);

        verify(authorRepository, times(1)).save(author);

        assertNotNull(savedAuthor);
        assertEquals("Test Author", savedAuthor.getName());
    }

    @Test
    public void testAddBookToAuthor() {
        Author author = new Author();
        author.setId(1);
        author.setName("Test Author");

        Book book = new Book();
        book.setId(1);
        book.setTitle("Test Book");

        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Author result = authorService.addBookToAuthor(1, 1);

        verify(authorRepository, times(1)).findById(1);
        verify(bookRepository, times(1)).findById(1);
        verify(authorRepository, times(1)).save(author);
        verify(bookRepository, times(1)).save(book);
        
        assertSame(author, result);
        assertTrue(author.getBooks().contains(book));
    }

    @Test
    public void testGetAuthorById() {
        int id = 1;
        Author author = new Author();
        author.setId(id);
        author.setName("Test Author");

        when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        Author result = authorService.getAuthorById(id);

        assertEquals(author, result);
        verify(authorRepository, times(1)).findById(id);
    }

    @Test
    public void testGetAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1, "Author1", new ArrayList<>()));
        authors.add(new Author(2, "Author2", new ArrayList<>()));

        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAllAuthors();

        assertEquals(2, result.size());
        verify(authorRepository).findAll();
    }

    @Test
    public void testUpdateAuthor() {
        int id = 1;
        Author newAuthor = new Author();
        newAuthor.setId(id);
        newAuthor.setName("New name");

        Author oldAuthor = new Author();
        oldAuthor.setId(id);
        oldAuthor.setName("Old name");

        when(authorRepository.findById(id)).thenReturn(Optional.of(oldAuthor));
        when(authorRepository.save(oldAuthor)).thenReturn(oldAuthor);

        Author updatedAuthor = authorService.updateAuthor(id, newAuthor);

        verify(authorRepository, times(1)).findById(id);
        verify(authorRepository, times(1)).save(oldAuthor);
        assertEquals("New name", newAuthor.getName());
    }

    @Test
    public void deleteAuthor() {
        Author author = new Author();
        author.setId(1);
        author.setName("Test Author");

        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        authorService.deleteAuthor(1);

        verify(authorRepository, times(1)).delete(author);
    }
}
