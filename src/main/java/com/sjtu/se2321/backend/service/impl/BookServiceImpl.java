package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.TagDAO;
import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.dto.BookEditBody;
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
    public PageResult<BookDTO> findBookByKeywordAndTag(int limit, int offset, String tagName, String keyword) {
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
        Page<Book> books = bookDAO.findAllByKeywordAndTag(limit, offset, tagId, keyword);
        List<BookDTO> dtos = new ArrayList<>();
        for (Book book : books.getContent()) {
            dtos.add(new BookDTO(book));
        }
        return new PageResult<BookDTO>(books.getTotalPages(), dtos);
    }

    @Override
    public Book findBookById(Long id) {
        return bookDAO.findById(id);
    }

    @Override
    @Transactional
    public void editBookInfo(Long id, BookEditBody body) {
        Book book = bookDAO.findById(id);
        BeanUtils.copyProperties(body, book);
        bookDAO.save(book);
    }

    @Override
    @Transactional
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Override
    public List<String> findAllTags() {
        return tagDAO.findAll().stream()
                .map(tag -> tag.getName())
                .toList();
    }

}