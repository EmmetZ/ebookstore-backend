package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.Order;

public interface OrderDAO {
    public List<Order> findAllByUserId(Long userId);

    public void save(Order order);
}
