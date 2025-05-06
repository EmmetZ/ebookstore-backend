package com.sjtu.se2321.backend.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.repository.BookRepository;

@Component
public class BookDAOImpl implements BookDAO {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks(int limit, int offset) {
        return bookRepository.findWithLimit(limit, offset);
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Integer getBookCount() {
        return bookRepository.count();
    }
}
