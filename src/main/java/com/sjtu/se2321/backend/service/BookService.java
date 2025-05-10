package com.sjtu.se2321.backend.service;

import java.util.List;
import java.util.Optional;

import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.entity.Tag;

public interface BookService {
    public PageResult<BookDTO> searchBooks(int limit, int offset, String tag, String keyword);

    public Optional<BookDTO> getBookById(Long id);

    // tag
    public List<String> getAllTags();

    public Optional<Tag> getTagByName(String name);
}
