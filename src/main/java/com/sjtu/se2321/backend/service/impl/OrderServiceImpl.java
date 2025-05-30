package com.sjtu.se2321.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.CartDAO;
import com.sjtu.se2321.backend.dao.OrderDAO;
import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.entity.CartItem;
import com.sjtu.se2321.backend.entity.Order;
import com.sjtu.se2321.backend.entity.OrderItem;
import com.sjtu.se2321.backend.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return orderDAO.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void placeOrder(Long userId, String address, String tel, String receiver, List<Long> itemIds) {
        Order order = new Order(userId, address, tel, receiver);
        int cost = 0;
        for (Long itemId : itemIds) {
            CartItem cartItem = cartDAO.findById(itemId);
            OrderItem item = new OrderItem(cartItem.getBook(), cartItem.getNumber());
            order.addOrdetItem(item);

            cartDAO.delete(cartItem.getId());
            bookDAO.updateBookSales(cartItem.getBook().getId(), cartItem.getNumber());
            cost += cartItem.getNumber() * bookDAO.findById(cartItem.getBook().getId()).getPrice();
        }
        orderDAO.save(order);
        userDAO.updateBalance(userId, -cost);
    }
}
