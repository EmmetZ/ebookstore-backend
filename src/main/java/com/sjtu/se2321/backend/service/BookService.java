package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.dto.BookEditBody;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.entity.Book;

public interface BookService {
    public PageResult<BookDTO> findBookByKeywordAndTag(int limit, int offset, String tag, String keyword);

    public Book findBookById(Long id);

    public void editBookInfo(Long id, BookEditBody body);

    public void save(Book book, List<String> tagNames);

    // tag
    public List<String> findAllTags();
}
