package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.CartItem;

public interface CartDAO {
    public List<CartItem> findAllByUserId(Long userId);

    public boolean updateCartItem(Long id, Integer number);

    public boolean addBookToCart(Long bookId, Long userId);

    public boolean deleteCartItem(Long id);
}
