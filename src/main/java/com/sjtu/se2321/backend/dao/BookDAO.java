package com.sjtu.se2321.backend.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.sjtu.se2321.backend.entity.Book;

public interface BookDAO {

    public Page<Book> findAllByKeywordAndTag(Specification<Book> spec, Pageable pageable);

    public Book findById(Long id);

    public void save(Book book);

    public Book getReferenceById(Long id);
}
