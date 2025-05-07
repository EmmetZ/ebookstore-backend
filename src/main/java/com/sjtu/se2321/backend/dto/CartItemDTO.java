package com.sjtu.se2321.backend.dto;

import com.sjtu.se2321.backend.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDTO {
    private Integer id;
    private Book book;
    private Integer number;
}
