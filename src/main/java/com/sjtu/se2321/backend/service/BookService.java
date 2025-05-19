package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Tag;

public interface BookService {
    public PageResult<Book> findAllByKeywordAndTag(int limit, int offset, String tag, String keyword);

    public BookDTO countByKeywordAndTag(Long id);

    // tag
    public List<String> getAllTags();

    public Tag getTagByName(String name);
}
