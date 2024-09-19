package com.jromeo.webservice;

import com.jromeo.webservice.book.entity.Book;
import com.jromeo.webservice.book.repository.BookRepository;
import com.jromeo.webservice.book.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


/**
 * Author: Sandra Jeppson Kristiansson
 */

@SpringBootTest
public class BookServiceTests {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setTitle("Book");
        book.setGenre("Genre");

        when(bookRepository.save(book)).thenReturn(book);

        Book createdBook = bookService.createBook(book);

        verify(bookRepository, times(1)).save(book);

        assertEquals(book, createdBook);
    }

    @Test
    public void testGetBookById() {
        Book newBook = new Book(1, "Title", "Genre", null);
        when(bookRepository.findById(1)).thenReturn(Optional.of(newBook));

        Book actualBook = bookService.getBookById(1);

        assertEquals(newBook, actualBook);
    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book(1, "Title1", "Genre1", null);
        Book book2 = new Book(2, "Title2", "Genre2", null);
        List<Book> mockedBooks = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(mockedBooks);

        List<Book> foundBooks = bookService.getAllBooks();

        verify(bookRepository, times(1)).findAll();

        assertNotNull(foundBooks);
        assertEquals(2, foundBooks.size());
        assertEquals("Title1", foundBooks.get(0).getTitle());
        assertEquals("Genre2", foundBooks.get(1).getGenre());
    }

    @Test
    public void testUpdateBook() {
        Book existingBook = new Book(1, "Old Title", "Old Genre", null);
        Book updatedBook = new Book(1, "New Title", "New Genre", null);

        when(bookRepository.findById(1)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        Book result = bookService.updateBook(1, updatedBook);

        assertEquals(updatedBook.getTitle(), result.getTitle());
        assertEquals(updatedBook.getGenre(), result.getGenre());
        verify(bookRepository, times(1)).findById(1);
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    public void testDeleteBook() {
        int id = 1;
        Book book = new Book();
        book.setId(1);
        
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        bookService.deleteBook(id);

        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).delete(book);
    }
}
