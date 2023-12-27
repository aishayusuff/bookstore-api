package com.practice.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.book.model.Book;

public class Repository {
    public interface BookRepository extends JpaRepository<Book, Long> {
        
    }
}
