package com.sjtu.se2321.backend.dto;

import java.util.Set;

import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Tag;

import lombok.Data;

@Data
public class AdminBookDTO {
    private Long id;
    private String title;
    private String author;
    private Integer price;
    private String description;
    private String cover;
    private Integer sales;
    private Integer stock;
    private Boolean isActive;
    private Set<Tag> tags;

    public AdminBookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.description = book.getDescription();
        this.cover = book.getCover().getFileName();
        this.sales = book.getSales();
        this.stock = book.getStock();
        this.tags = book.getTags();
        this.isActive = book.getIsActive();
    }
}
