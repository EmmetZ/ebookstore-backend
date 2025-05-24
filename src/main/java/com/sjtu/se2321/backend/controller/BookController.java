package com.sjtu.se2321.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.BookEditBody;
import com.sjtu.se2321.backend.dto.BookReqParam;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.service.BookService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        return ResponseEntity.ok(bookService.findBookByKeywordAndTag(size, index * size, tag, keyword));
    }

    @GetMapping("/api/book/tags")
    public ResponseEntity<List<String>> findAllTags() {
        return ResponseEntity.ok(bookService.findAllTags());
    }

    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @PutMapping("/api/book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Result<Void>> editBookInfo(@PathVariable Long id, @RequestBody BookEditBody body) {
        bookService.editBookInfo(id, body);
        return ResponseEntity.ok(Result.success("修改成功"));
    }
}
