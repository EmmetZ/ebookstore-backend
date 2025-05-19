package com.sjtu.se2321.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.dto.BookReqParam;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.service.BookService;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public ResponseEntity<PageResult<Book>> searchBooks(BookReqParam reqParam) {
        Integer size = reqParam.getPageSize();
        Integer index = reqParam.getPageIndex();
        String keyword = reqParam.getKeyword().trim();
        String tag = reqParam.getTag().trim();

        if (index == null || size == null || index < 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookService.findAllByKeywordAndTag(size, index * size, tag, keyword));
    }

    @GetMapping("/api/book/tags")
    public ResponseEntity<List<String>> getAllTags() {
        return ResponseEntity.ok(bookService.getAllTags());
    }

    @GetMapping("/api/book/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.countByKeywordAndTag(id);
        if (bookDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookDTO);
    }

}
