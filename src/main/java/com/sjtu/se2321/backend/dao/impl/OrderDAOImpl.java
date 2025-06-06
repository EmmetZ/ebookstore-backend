package com.sjtu.se2321.backend.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Page<Order> findAll(Specification<Order> spec, Pageable pageable) {
        return orderRepository.findAll(spec, pageable);
    }

}
