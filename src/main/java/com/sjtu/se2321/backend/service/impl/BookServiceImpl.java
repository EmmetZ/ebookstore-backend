package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.TagDAO;
import com.sjtu.se2321.backend.dto.BookDTO;
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
    public List<BookDTO> searchBooks(int limit, int offset, int tagId, String keyword) {
        // if (keyword == null || keyword.isEmpty()) {
        // keyword = "";
        // }
        List<Book> books = bookDAO.searchBooks(limit, offset, tagId, keyword);
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            List<Tag> tags = tagDAO.getTagByBookId(book.getId()).orElse(new ArrayList<>());
            bookDTOs.add(BookDTO.fromBook(book, tags));
        }
        return bookDTOs;
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        return bookDAO.getBookById(id);
    }

    @Override
    public Integer countSearchResult(int tagId, String keyword) {
        return bookDAO.countSearchResult(tagId, keyword);
    }

    @Override
    public int getTotal(int pageSize) {
        int total = bookDAO.countSearchResult(0, "");
        return (int) Math.ceil((double) total / pageSize);
    }
}