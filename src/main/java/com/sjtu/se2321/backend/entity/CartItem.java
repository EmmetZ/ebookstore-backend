package com.sjtu.se2321.backend.entity;

import lombok.Data;

@Data
public class CartItem {
    Long id;
    Long userId;
    Long bookId;
    Integer number;
}
