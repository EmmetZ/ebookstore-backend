package com.sjtu.se2321.backend.dto;

import com.sjtu.se2321.backend.entity.OrderItem;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Integer number;
    private BookDTO book;

    public OrderItemDTO(OrderItem item) {
        this.id = item.getId();
        this.number = item.getNumber();
    }
}
