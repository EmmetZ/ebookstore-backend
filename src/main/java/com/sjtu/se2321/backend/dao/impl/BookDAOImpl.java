package com.sjtu.se2321.backend.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.repository.BookRepository;
import com.sjtu.se2321.backend.repository.specification.BookSpecifications;

@Component
public class BookDAOImpl implements BookDAO {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> findAllByKeywordAndTag(int limit, int offset, Long tagId, String keyword) {
        Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by("id").ascending());
        Specification<Book> spec = BookSpecifications.withFilters(tagId, keyword);
        return bookRepository.findAll(spec, pageable);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Book not found with id: " + id));
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getReferenceById(Long id) {
        return bookRepository.getReferenceById(id);
    }
}
