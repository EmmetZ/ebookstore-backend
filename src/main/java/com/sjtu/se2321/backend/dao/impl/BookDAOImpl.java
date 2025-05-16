package com.sjtu.se2321.backend.dao.impl;

import java.util.List;

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
    public List<Book> searchBooks(int limit, int offset, Long tagId, String keyword) {
        return bookRepository.searchBooks(limit, offset, tagId, keyword);
    }

    @Override
    public Integer countSearchResult(Long tagId, String keyword) {
        return bookRepository.countSearchResult(tagId, keyword);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void updateBookSale(Long id, int sales) {
        bookRepository.updateBookSale(id, sales);
    }
}
