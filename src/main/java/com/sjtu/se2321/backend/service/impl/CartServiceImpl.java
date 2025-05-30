package com.sjtu.se2321.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.CartDAO;
import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.CartItem;
import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<CartItem> findAllByUserId(Long userId) {
        return cartDAO.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void updateCartItem(Long id, Integer number) {
        cartDAO.updateCartItem(id, number);
    }

    @Override
    @Transactional
    public void save(Long bookId, Long userId) {
        Book book = bookDAO.getReferenceById(bookId);
        User user = userDAO.getReferenceById(userId);
        cartDAO.save(new CartItem(book, user));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cartDAO.delete(id);
    }
}
