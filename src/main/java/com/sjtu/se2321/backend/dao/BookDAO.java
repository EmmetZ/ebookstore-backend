package com.sjtu.se2321.backend.dao;

import org.springframework.data.domain.Page;

import com.sjtu.se2321.backend.entity.Book;

public interface BookDAO {

    public Page<Book> findAllByKeywordAndTag(int limit, int offset, Long tagId, String keyword);

    public Book findById(Long id);

    public void updateBookSales(Long id, int sales);

    public void save(Book book);

    public Book getReferenceById(Long id);
}
