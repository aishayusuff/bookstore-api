package com.practice.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.book.model.Book;
import com.practice.book.repository.Repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
    private final BookRepository repository;

    // @Autowired
    public BookService (BookRepository repository) {
        this.repository = repository;
    }


    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookByID(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book with id: " + id + " Not found"));
    }

}
