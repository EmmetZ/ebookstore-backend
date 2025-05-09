package com.sjtu.se2321.backend.dto;

import java.util.List;

import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Tag;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Integer price;
    private String description;
    private String cover;
    private Integer sales;

    private List<Tag> tags;

    public static BookDTO fromBook(Book book, List<Tag> tags) {
        return new BookDTO() {
            {
                setId(book.getId());
                setTitle(book.getTitle());
                setAuthor(book.getAuthor());
                setPrice(book.getPrice());
                setDescription(book.getDescription());
                setCover(book.getCover());
                setSales(book.getSales());
                setTags(tags);
            }
        };
    }
}
