package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.TagDAO;
import com.sjtu.se2321.backend.dto.BookDTO;
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
    public PageResult<BookDTO> searchBooks(int limit, int offset, String tagName, String keyword) {
        // tag:
        // tagId = -1 : tag is null, search all books
        // tagId = 0 : tag is not found in db
        // tagId > 0 : tag is found in db
        Long tagId = Long.valueOf(-1);
        if (!tagName.isEmpty()) {
            Tag tag = tagDAO.getTagByName(tagName);
            if (tag != null) {
                tagId = tag.getId();
            } else {
                tagId = Long.valueOf(0);
            }
        }
        List<Book> books = bookDAO.searchBooks(limit, offset, tagId, keyword);
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            List<Tag> tags = tagDAO.getTagByBookId(book.getId());
            bookDTOs.add(BookDTO.fromBook(book, tags));
        }
        int num = bookDAO.countSearchResult(tagId, keyword);
        int total = (int) Math.ceil((double) num / limit);
        return new PageResult<BookDTO>(total, bookDTOs);
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookDAO.getBookById(id);
        if (book != null) {
            List<Tag> tags = tagDAO.getTagByBookId(book.getId());
            return BookDTO.fromBook(book, tags);
        }
        return null;
    }

    @Override
    public List<String> getAllTags() {
        return tagDAO.getAllTags().stream()
                .map(tag -> tag.getName())
                .toList();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDAO.getTagByName(name);
    }
}