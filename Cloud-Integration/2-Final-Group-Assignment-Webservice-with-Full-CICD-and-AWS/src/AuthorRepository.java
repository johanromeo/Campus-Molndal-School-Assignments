package com.jromeo.webservice.book.repository;

import com.jromeo.webservice.book.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Johan Romeo
 */
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
