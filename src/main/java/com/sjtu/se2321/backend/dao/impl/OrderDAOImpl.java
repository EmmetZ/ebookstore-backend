package com.sjtu.se2321.backend.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return orderRepository.findAllByUserId(userId, sort);
    }

    @Override
    public Long save(Long userId, String address, String tel, String receiver) {
        return orderRepository.save(new Order(userId, address, tel, receiver)).getId();
    }

}
