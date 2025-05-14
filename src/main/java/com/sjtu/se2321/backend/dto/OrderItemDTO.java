package com.sjtu.se2321.backend.dto;

import com.sjtu.se2321.backend.entity.Book;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Book book;
    private Integer number;
}
