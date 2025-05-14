package com.sjtu.se2321.backend.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.OrderDAO;
import com.sjtu.se2321.backend.entity.Order;
import com.sjtu.se2321.backend.repository.OrderRepository;

@Component
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

}
