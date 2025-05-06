package com.sjtu.se2321.backend.dao;

import java.util.List;
import java.util.Optional;

import com.sjtu.se2321.backend.entity.Book;

public interface BookDAO {

    public List<Book> getBooks(int limit, int offset);

    public Optional<Book> getBookById(Integer id);

    public Integer getBookCount();

}
