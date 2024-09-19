package com.jromeo.webservice.book.repository;

import com.jromeo.webservice.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Johan Romeo
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

}
