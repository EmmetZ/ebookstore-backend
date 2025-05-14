package com.sjtu.se2321.backend.entity;

import lombok.Data;

@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long bookId;
    private Integer number;
}
