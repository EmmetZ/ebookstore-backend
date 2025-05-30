package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.entity.CartItem;

public interface CartService {
    public List<CartItem> findAllByUserId(Long userId);

    public void updateCartItem(Long id, Integer number);

    public void save(Long bookId, Long userId);

    public void delete(Long id);
}
