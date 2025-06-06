package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtu.se2321.backend.dao.BookDAO;
import com.sjtu.se2321.backend.dao.CartDAO;
import com.sjtu.se2321.backend.dao.OrderDAO;
import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.dto.BookDTO;
import com.sjtu.se2321.backend.dto.OrderDTO;
import com.sjtu.se2321.backend.dto.OrderItemDTO;
import com.sjtu.se2321.backend.entity.Book;
import com.sjtu.se2321.backend.entity.CartItem;
import com.sjtu.se2321.backend.entity.Order;
import com.sjtu.se2321.backend.entity.OrderItem;
import com.sjtu.se2321.backend.entity.User;
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
    public List<OrderDTO> findAllByUserId(Long userId) {
        List<Order> orders = orderDAO.findAllByUserId(userId);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> items = order.getItems();
            List<OrderItemDTO> dtos = new ArrayList<>();
            OrderDTO orderDTO = new OrderDTO(order);
            for (OrderItem item : items) {
                Book book = item.getBook();
                BookDTO bookDTO = new BookDTO(book);
                OrderItemDTO dto = new OrderItemDTO(item);
                dto.setBook(bookDTO);
                dtos.add(dto);
            }
            orderDTO.setItems(dtos);
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
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
            Book book = cartItem.getBook();
            book.setSales(book.getSales() + cartItem.getNumber());
            book.setStock(book.getStock() - cartItem.getNumber());
            bookDAO.save(book);
            cost += cartItem.getNumber() * bookDAO.findById(cartItem.getBook().getId()).getPrice();
        }
        orderDAO.save(order);
        User user = userDAO.findById(userId);
        user.setBalance(user.getBalance() - cost);
        userDAO.save(user);
    }
}
