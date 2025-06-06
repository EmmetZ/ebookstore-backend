package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.dto.CartItemDTO;

public interface CartService {
    public List<CartItemDTO> findAllByUserId(Long userId);

    public void updateCartItem(Long id, Integer number);

    public void save(Long bookId, Long userId);

    public void delete(Long id);
}
