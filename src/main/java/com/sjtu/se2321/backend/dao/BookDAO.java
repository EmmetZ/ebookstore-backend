package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.Book;

public interface BookDAO {

    public List<Book> searchBooks(int limit, int offset, Long tagId, String keyword);

    public Integer countSearchResult(Long tagId, String keyword);

    public Book getBookById(Long id);

}
