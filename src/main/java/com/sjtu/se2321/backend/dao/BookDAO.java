package com.sjtu.se2321.backend.dao;

import java.util.List;
import java.util.Optional;

import com.sjtu.se2321.backend.entity.Book;

public interface BookDAO {

    public List<Book> searchBooks(int limit, int offset, int tagId, String keyword);

    public Integer countSearchResult(int tagId, String keyword);

    public Optional<Book> getBookById(Integer id);

}
