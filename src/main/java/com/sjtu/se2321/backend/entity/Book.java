package com.sjtu.se2321.backend.entity;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer price;
    private String description;
    private String cover;
    private Integer sales;
}
