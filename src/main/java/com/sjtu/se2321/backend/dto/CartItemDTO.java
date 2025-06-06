package com.sjtu.se2321.backend.dto;

import com.sjtu.se2321.backend.entity.CartItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private BookDTO book;
    private Integer number;

    public CartItemDTO(CartItem item) {
        this.id = item.getId();
        this.number = item.getNumber();
    }
}
