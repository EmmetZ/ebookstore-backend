package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.CartDAO;
import com.sjtu.se2321.backend.dto.CartItemDTO;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.CartItem;
import com.sjtu.se2321.backend.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<CartItemDTO> findAllByUserId(Long userId) {
        List<CartItem> items = cartDAO.findAllByUserId(userId);
        if (items.isEmpty()) {
            return List.of();
        }
        List<CartItemDTO> cart = new ArrayList<>();
        for (CartItem item : items) {
            Book book = bookDAO.findById(item.getBookId());
            cart.add(new CartItemDTO(item.getId(), book, item.getNumber()));
        }
        return cart;
    }

    @Override
    @Transactional
    public void updateCartItem(Long id, Integer number) {
        cartDAO.updateCartItem(id, number);
    }

    @Override
    @Transactional
    public void save(Long bookId, Long userId) {
        cartDAO.save(bookId, userId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cartDAO.delete(id);
    }
}
