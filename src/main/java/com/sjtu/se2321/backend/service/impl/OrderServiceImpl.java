package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.CartDAO;
import com.sjtu.se2321.backend.dao.OrderDAO;
import com.sjtu.se2321.backend.dao.OrderItemDAO;
import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.dto.OrderDTO;
import com.sjtu.se2321.backend.dto.OrderItemDTO;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.CartItem;
import com.sjtu.se2321.backend.entity.Order;
import com.sjtu.se2321.backend.entity.OrderItem;
import com.sjtu.se2321.backend.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<OrderDTO> findAllByUserId(Long userId) {
        List<Order> orders = orderDAO.findAllByUserId(userId);
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (Order order : orders) {
            Long orderId = order.getId();
            List<OrderItem> orderItems = orderItemDAO.findAllByOrderId(orderId);

            OrderDTO orderDTO = new OrderDTO(order);
            for (OrderItem orderItem : orderItems) {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setId(orderItem.getId());
                Book book = bookDAO.findById(orderItem.getBookId());
                orderItemDTO.setBook(book);
                orderItemDTO.setNumber(orderItem.getNumber());
                orderDTO.addItem(orderItemDTO);
            }
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }

    @Override
    @Transactional
    public void placeOrder(Long userId, String address, String tel, String receiver, List<Long> itemIds) {
        Long orderId = orderDAO.save(userId, address, tel, receiver);
        int cost = 0;
        for (Long itemId : itemIds) {
            CartItem cartItem = cartDAO.findById(itemId);
            orderItemDAO.save(orderId, cartItem.getBookId(), cartItem.getNumber());
            cartDAO.delete(cartItem.getId());
            bookDAO.updateBookSales(cartItem.getBookId(), cartItem.getNumber());
            cost += cartItem.getNumber() * bookDAO.findById(cartItem.getBookId()).getPrice();
        }
        userDAO.updateBalance(userId, -cost);
    }
}
