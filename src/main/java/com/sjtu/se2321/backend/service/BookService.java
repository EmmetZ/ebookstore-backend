package com.sjtu.se2321.backend.service;

import java.util.List;
import java.util.Optional;

import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.entity.Book;

public interface BookService {
    public List<BookDTO> searchBooks(int limit, int offset, int tagId, String keyword);

    public Optional<Book> getBookById(Integer id);

    public Integer countSearchResult(int tagId, String keyword);

    public int getTotal(int pageSize);
}
