package com.sjtu.se2321.backend.dto;

import lombok.Data;

@Data
public class BookEditBody {
    private String title;
    private String author;
    private Integer price;
    private String description;
    private Integer stock;
}
