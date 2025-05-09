package com.sjtu.se2321.backend.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.CartDAO;
import com.sjtu.se2321.backend.entity.CartItem;
import com.sjtu.se2321.backend.repository.CartRepository;

@Component
public class CartDAOImpl implements CartDAO {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<CartItem> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    @Override
    public boolean updateCartItem(Long id, Integer number) {
        return cartRepository.updateCartItem(id, number); 
    }
}
