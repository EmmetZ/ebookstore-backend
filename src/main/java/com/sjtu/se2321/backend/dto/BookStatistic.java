package com.sjtu.se2321.backend.dto;

import java.util.Set;

import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Tag;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookStatistic {
    private Long id;
    private String title;
    private String author;
    private Integer price;
    private String description;
    private String cover;

    // purchase number
    private Integer number;
    private Set<Tag> tags;

    public BookStatistic(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.description = book.getDescription();
        this.tags = book.getTags();        
    }
}
