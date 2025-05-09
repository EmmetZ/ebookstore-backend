package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.dto.CartItemDTO;

public interface CartService {
    public List<CartItemDTO> getCartItemsByUserId(Long userId);

    public boolean updateCartItem(Long id, Integer number);
}
