package com.sjtu.se2321.backend.entity;

import lombok.Data;

@Data
public class CartItem {
    Integer id;
    Integer userId;
    Integer bookId;
    Integer number;
}
