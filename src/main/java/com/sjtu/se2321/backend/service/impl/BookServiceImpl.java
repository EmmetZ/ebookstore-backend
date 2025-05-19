package com.sjtu.se2321.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.TagDAO;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Tag;
import com.sjtu.se2321.backend.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private TagDAO tagDAO;

    @Override
    public PageResult<Book> findBookByKeywordAndTag(int limit, int offset, String tagName, String keyword) {
        // tag:
        // tagId = -1 : tag is null, search all books
        // tagId = 0 : tag is not found in db
        // tagId > 0 : tag is found in db
        Long tagId = Long.valueOf(-1);
        if (!tagName.isEmpty()) {
            Tag tag = tagDAO.findByName(tagName);
            if (tag != null) {
                tagId = tag.getId();
            } else {
                tagId = Long.valueOf(0);
            }
        }
        List<Book> books = bookDAO.findAllByKeywordAndTag(limit, offset, tagId, keyword);
        long num = bookDAO.countByKeywordAndTag(tagId, keyword);
        int total = (int) Math.ceil((double) num / limit);
        return new PageResult<Book>(total, books);
    }


    @Override
    public Book findBookById(Long id) {
        return bookDAO.findById(id);
    }

    @Override
    public List<String> findAllTags() {
        return tagDAO.findAll().stream()
                .map(tag -> tag.getName())
                .toList();
    }

}