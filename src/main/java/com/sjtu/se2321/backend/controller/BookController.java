package com.sjtu.se2321.backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sjtu.se2321.backend.Utils;
import com.sjtu.se2321.backend.dto.BookAddBody;
import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.dto.BookEditBody;
import com.sjtu.se2321.backend.dto.BookReqParam;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.BookCover;
import com.sjtu.se2321.backend.entity.Image;
import com.sjtu.se2321.backend.service.BookService;
import com.sjtu.se2321.backend.service.ImageService;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ImageService imageService;

    @Value("${file.upload-cover-dir}")
    private String uploadDir;

    @GetMapping("/api/books")
    public ResponseEntity<PageResult<BookDTO>> searchBooks(BookReqParam reqParam) {
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
    public ResponseEntity<BookDTO> findBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return ResponseEntity.ok(new BookDTO(book));
    }

    @PutMapping("/api/book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Result<Void>> editBookInfo(@PathVariable Long id, @RequestBody BookEditBody body) {
        bookService.editBookInfo(id, body);
        return ResponseEntity.ok(Result.success("修改成功"));
    }

    @PostMapping("/api/book/{id}/cover")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Result<?>> uploadCover(@PathVariable Long id, @RequestParam MultipartFile file)
            throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        String fileName = UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(file.getOriginalFilename());
        Path filePath = uploadPath.resolve(fileName);
        String preCover;

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // 保存封面图片
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            if (id != 0) {
                // save to Image table
                Image cover = bookService.findBookById(id).getCover();
                preCover = cover.getFileName();
                cover.setFileName(fileName);
                imageService.save(cover);

                // delete previous cover
                if (preCover != null) {
                    Path prePath = uploadPath.resolve(preCover);
                    Files.deleteIfExists(prePath);
                }
                return ResponseEntity.ok(Result.success("上传成功")); // 返回图片URL给前端
            } else {
                Image image = new BookCover(fileName);
                Image imageSaved = imageService.save(image);

                HashMap<String, Object> info = new HashMap<>();
                info.put("id", imageSaved.getId());
                info.put("fileName", fileName);
                return ResponseEntity.ok(Result.success("上传成功", info));
            }
        } catch (Exception e) {
            // if file is saved, delete it
            Files.deleteIfExists(filePath);
            throw e;
        }
    }

    @GetMapping("/api/book/covers/{fileName}")
    public ResponseEntity<Resource> getCover(@PathVariable String fileName) throws IOException {
        return Utils.getImageResource(uploadDir, fileName);
    }

    @PostMapping("/api/book")
    public ResponseEntity<Result<Void>> addBook(@RequestBody BookAddBody body) {
        Book book = new Book(body);
        if (body.getCoverId() != 0) {
            Image image = imageService.getReferenceById(body.getCoverId());
            book.setCover(image);
        } else {
            Image image = new Image();
            image.setFileName("default_cover.jpg");
            image = imageService.save(image);
            book.setCover(image);
        }
        bookService.save(book, body.getTags());
        return ResponseEntity.ok(Result.success("添加书籍成功"));
    }

}
