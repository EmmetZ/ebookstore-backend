package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<CartItemDTO> getCartItemsByUserId(Integer userId) {
        List<CartItem> items = cartDAO.findAllByUserId(userId);
        if (items.isEmpty()) {
            return List.of();
        }
        List<CartItemDTO> cart = new ArrayList<>();
        for (CartItem item : items) {
            Optional<Book> bookOpt = bookDAO.getBookById(item.getBookId());
            cart.add(new CartItemDTO(item.getId(), bookOpt.get(), item.getNumber()));
        }
        return cart;
    }

}
