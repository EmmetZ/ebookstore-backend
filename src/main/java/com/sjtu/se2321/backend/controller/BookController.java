package com.sjtu.se2321.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.dto.BookReqParam;
import com.sjtu.se2321.backend.dto.ListResult;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.service.BookService;
import com.sjtu.se2321.backend.service.TagService;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private TagService tagService;

    @GetMapping("/api/books")
    public ResponseEntity<ListResult<BookDTO>> searchBooks(BookReqParam reqParam) {
        if (reqParam.getPageIndex() == null ||
                reqParam.getPageSize() == null ||
                reqParam.getPageIndex() < 0 ||
                reqParam.getPageSize() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        System.out.println(reqParam);
        int pageSize = reqParam.getPageSize();
        int pageIndex = reqParam.getPageIndex();
        List<BookDTO> books = bookService.searchBooks(pageSize, pageIndex);
        int total = bookService.getTotal(pageSize);
        return ResponseEntity.ok(new ListResult<>(total, books));
    }

    @GetMapping("/api/book/tags")
    public ResponseEntity<List<String>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping("/api/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book.get());
    }

}
