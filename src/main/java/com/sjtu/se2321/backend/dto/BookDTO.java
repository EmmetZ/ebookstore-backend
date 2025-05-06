package com.sjtu.se2321.backend.dto;

import java.util.List;

import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.Tag;

public class BookDTO {
    private Integer id;
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

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
