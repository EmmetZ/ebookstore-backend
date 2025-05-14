package com.sjtu.se2321.backend.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.OrderItemDAO;
import com.sjtu.se2321.backend.entity.OrderItem;
import com.sjtu.se2321.backend.repository.OrderItemRepository;

@Component
public class OrderItemDAOImpl implements OrderItemDAO {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAllByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }

    @Override
    public void addOrderItem(Long orderId, Long bookId, Integer number) {
        orderItemRepository.addOrderItem(orderId, bookId, number);
    }
}
